package com.example.pagingsample.datasource.paging.base

import androidx.paging.*
import com.example.pagingsample.datasource.local.AppDatabase
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.DatabaseTest
import com.example.pagingsample.datasource.local.dao.base.HiltTest
import com.example.pagingsample.datasource.local.helper.ClearAllItemsAndKeysDaoHelper
import com.example.pagingsample.datasource.local.helper.SaveItemsWithRemoteKeysDaoHelper
import com.example.pagingsample.datasource.remote.helper.base.PagingApiHelper
import com.example.pagingsample.model.local.interfaces.Identifiable
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
abstract class BaseRemoteMediatorTest<T : Identifiable> : DatabaseTest, HiltTest {

    @Inject
    override lateinit var db: AppDatabase

    @Inject
    lateinit var saveDataWithRemoteKeysDaoHelper: SaveItemsWithRemoteKeysDaoHelper<T>

    @Inject
    lateinit var remoteKeyDao: RemoteKeyDao

    @Inject
    lateinit var cleanerDaoHelper: ClearAllItemsAndKeysDaoHelper<T>

    @Inject
    lateinit var loadDispatcher: CoroutineDispatcher

    open lateinit var pagingApiHelper: PagingApiHelper<*, T>

    lateinit var baseRemoteMediator: BaseRemoteMediator<T>

    abstract val itemList: List<T>

    /**
     * Init API with data or Error.
     */
    abstract fun initPagingApiHelper(data: List<T>, throwError: Boolean = false)

    /**
     * Function requires [pagingApiHelper] to be set up.
     */
    abstract fun initRemoteMediator()

    private fun initRemoteMediatorWithData(
        data: List<T> = emptyList(),
        throwError: Boolean = false
    ) {
        initPagingApiHelper(data, throwError)
        initRemoteMediator()
    }

    private fun getRefreshPagingState() : PagingState<Int, T> {
        return PagingState(
            listOf(),
            null,
            PagingConfig(10),
            10,
        )
    }

    @Test
    fun refreshPagingDataWhenMoreDataIsPresent() = runTest(loadDispatcher) {
        initRemoteMediatorWithData(itemList)

        val result = baseRemoteMediator.load(LoadType.REFRESH, getRefreshPagingState())

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Success::class.java)
        assertThat((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached).isFalse()
    }

    @Test
    fun refreshPagingDataWhenEndOfPagingIsReached() = runTest(loadDispatcher) {
        initRemoteMediatorWithData(emptyList())

        val result = baseRemoteMediator.load(LoadType.REFRESH, getRefreshPagingState())

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Success::class.java)
        assertThat((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached).isTrue()

    }

    @Test
    fun refreshPagingDataWithNetworkError() = runTest(loadDispatcher) {
        initRemoteMediatorWithData(throwError = true)

        val result = baseRemoteMediator.load(LoadType.REFRESH, getRefreshPagingState())

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Error::class.java)
    }


}