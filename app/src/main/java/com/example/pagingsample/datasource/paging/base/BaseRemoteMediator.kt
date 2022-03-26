package com.example.pagingsample.datasource.paging.base

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.helper.base.CleanerDaoHelper
import com.example.pagingsample.datasource.local.helper.base.SaveItemWithRemoteKeysDaoHelper
import com.example.pagingsample.datasource.paging.*
import com.example.pagingsample.model.local.RemoteKey
import com.example.pagingsample.model.local.character.Character
import com.example.pagingsample.model.local.interfaces.Identifiable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
abstract class BaseRemoteMediator<T : Identifiable> constructor(
    private val saveDataDaoHelper: SaveItemWithRemoteKeysDaoHelper<T>,
    private val remoteKeyDao: RemoteKeyDao,
    private val cleanerDaoHelper: CleanerDaoHelper,
    private val loadDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : RemoteMediator<Int, T>() {

    abstract val startPage: Int

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, T>
    ) = withContext(loadDispatcher) {
        val page = when(val loadTypeResult = handleLoadType(loadType, state)) {
            is LoadTypeResult.PageResult -> {
                loadTypeResult.page
            }
            is LoadTypeResult.MediatorSuccessResult -> {
                return@withContext loadTypeResult.success
            }
        }

        if (loadType == LoadType.REFRESH) {
            cleanerDaoHelper.clear()
        }

        loadNewData(page, state)
    }

    /**
     * Converts paging [LoadType] to [LoadTypeResult].
     */
    @WorkerThread
    private suspend fun handleLoadType(
        loadType: LoadType,
        state: PagingState<Int, T>
    ): LoadTypeResult = when (loadType) {
        LoadType.REFRESH -> {
            Log.d("tag1", "REFTESH")
            val remoteKey = state.getClosestRemoteKeyForCurrentPosition()
            val position = remoteKey?.nextKey?.minus(1) ?: startPage
            PageResult(position)
        }
        LoadType.PREPEND -> {
            Log.d("tag1", "PREPEND")

            val remoteKey = state.getRemoteKeyFromFirstLoadedPage()
            remoteKey?.prevKey.getLoadTypeResult(remoteKey)
        }
        LoadType.APPEND -> {
            Log.d("tag1", "APPEBD")

            val remoteKey = state.getRemoteKeyFromLastLoadedPage()
            remoteKey?.nextKey.getLoadTypeResult(remoteKey)
        }
    }

    @WorkerThread
    protected suspend fun PagingState<Int, T>.getClosestRemoteKeyForCurrentPosition() =
        getItemRemoteKey(findClosestItemToCurrentPosition())

    @WorkerThread
    protected suspend fun PagingState<Int, T>.getRemoteKeyFromFirstLoadedPage() =
        getItemRemoteKey(findFirstLoadedItem())

    @WorkerThread
    protected suspend fun PagingState<Int, T>.getRemoteKeyFromLastLoadedPage() =
        getItemRemoteKey(findLastLoadedItem())


    /**
     * Ensure that function is executing in [WorkerThread] cause
     * it triggers database request.
     */
    @WorkerThread
    private suspend fun getItemRemoteKey(item: Identifiable?) =
        item?.let { remoteKeyDao.get(it.id) }


    private fun Int?.getLoadTypeResult(remoteKey: RemoteKey?): LoadTypeResult {
        return this?.let { prevKey ->
            PageResult(prevKey)
        } ?: MediatorSuccessResult(remoteKey != null)
    }

    @WorkerThread
    private suspend fun loadNewData(
        page: Int,
        state: PagingState<Int, T>
    ) = runPagingCatchingException {
        val data = loadDataFromServer(page, state)
        val isEndOfPaginationReached = data.isNullOrEmpty()

        val prevKey = getPrevKey(page)
        val nextKey = getNextKey(page, isEndOfPaginationReached)

        val remoteKeys = data.map { RemoteKey(it.id, prevKey, nextKey) }

        saveDataToCache(data, remoteKeys)

        MediatorResult.Success(isEndOfPaginationReached)
    }

    @WorkerThread
    abstract suspend fun loadDataFromServer(
        page: Int,
        state: PagingState<Int, T>,
    ) : List<T>

    @WorkerThread
    private suspend fun saveDataToCache(
        data: List<T>,
        remoteKeys: List<RemoteKey>,
    ) = saveDataDaoHelper.save(data, remoteKeys)


    protected fun getPrevKey(page: Int): Int? {
        return if (page == startPage) null
        else page.prevKey
    }

    protected fun getNextKey(page: Int, isEndOfPaginationReached: Boolean): Int? {
        return if (isEndOfPaginationReached) null
        else page.nextKey
    }

}