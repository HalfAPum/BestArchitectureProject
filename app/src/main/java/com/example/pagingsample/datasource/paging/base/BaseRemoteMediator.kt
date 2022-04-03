package com.example.pagingsample.datasource.paging.base

import androidx.annotation.WorkerThread
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.helper.ClearAllItemsAndKeysDaoHelper
import com.example.pagingsample.datasource.local.helper.SaveItemsWithRemoteKeysDaoHelper
import com.example.pagingsample.datasource.remote.helper.base.PagingApiHelper
import com.example.pagingsample.model.local.RemoteKey
import com.example.pagingsample.model.local.interfaces.Identifiable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
abstract class BaseRemoteMediator<T : Identifiable> constructor(
    private val saveDataWithRemoteKeysDaoHelper: SaveItemsWithRemoteKeysDaoHelper<T>,
    private val remoteKeyDao: RemoteKeyDao,
    private val cleanerDaoHelper: ClearAllItemsAndKeysDaoHelper<T>,
    private val pagingApiHelper: PagingApiHelper<*, T>,
    private val loadDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : RemoteMediator<Int, T>() {

    /**
     * Start page for paging(Some API's have different start page).
     */
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

        runPagingCatchingException {
            val data = loadDataFromServer(page)
            data.saveToCache(page)
        }
    }

    /**
     * Converts paging [LoadType] to [LoadTypeResult].
     * If there is page to load returns [LoadTypeResult.PageResult] with [LoadTypeResult.PageResult.page]
     * otherwise returns [LoadTypeResult.MediatorSuccessResult] with [RemoteMediator.MediatorResult.Success].
     */
    @WorkerThread
    private suspend fun handleLoadType(
        loadType: LoadType,
        state: PagingState<Int, T>
    ): LoadTypeResult = when (loadType) {
        LoadType.REFRESH -> {
            //Clear needed tables if this is new load
            cleanerDaoHelper.clearTables()

            //Calculate page
            val remoteKey = state.getClosestRemoteKeyForCurrentPosition()
            val position = remoteKey?.nextKey?.minus(1) ?: startPage
            PageResult(position)
        }
        LoadType.PREPEND -> {
            val remoteKey = state.getRemoteKeyFromFirstLoadedPage()
            remoteKey?.prevKey.getLoadTypeResult(remoteKey)
        }
        LoadType.APPEND -> {
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
        item?.let { remoteKeyDao.getById(it.id) }


    private fun Int?.getLoadTypeResult(remoteKey: RemoteKey?): LoadTypeResult {
        return this?.let { prevKey ->
            PageResult(prevKey)
        } ?: MediatorSuccessResult(remoteKey != null)
    }

    @WorkerThread
    suspend fun loadDataFromServer(page: Int) = pagingApiHelper.load(page)

    @WorkerThread
    private suspend fun List<T>.saveToCache(
        page: Int,
    ): MediatorResult.Success {
        val isEndOfPaginationReached = calculateEndOfPagination()

        val prevKey = getPrevKey(page)
        val nextKey = getNextKey(page, isEndOfPaginationReached)

        val remoteKeys = map { RemoteKey(it.id, prevKey, nextKey) }

        saveDataWithRemoteKeysDaoHelper.save(this, remoteKeys)

        return MediatorResult.Success(isEndOfPaginationReached)
    }

    /**
     * If no data is loaded then end of pagination is reached.
     */
    private fun List<Any>.calculateEndOfPagination(): Boolean {
        return isNullOrEmpty()
    }


    private fun getPrevKey(page: Int): Int? {
        return if (page == startPage) null
        else page.prevKey
    }

    private fun getNextKey(page: Int, isEndOfPaginationReached: Boolean): Int? {
        return if (isEndOfPaginationReached) null
        else page.nextKey
    }

}