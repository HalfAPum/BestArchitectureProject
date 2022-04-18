package com.example.pagingsample.datasource.paging.base

import androidx.paging.*
import com.example.pagingsample.MockitoTest
import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.helper.ClearAllItemsAndKeysDaoHelper
import com.example.pagingsample.datasource.local.helper.RemoteMediatorDaoHelper
import com.example.pagingsample.datasource.local.helper.SaveItemsWithRemoteKeysDaoHelper
import com.example.pagingsample.datasource.paging.BaseRemoteMediator
import com.example.pagingsample.datasource.remote.helper.IPagingApiHelper
import com.example.pagingsample.model.interfaces.Identifiable
import com.example.pagingsample.utils.EmulatedData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
class BaseRemoteMediatorTest : MockitoTest {

    @Mock
    private lateinit var itemDao: BaseDao<Identifiable>

    @Mock
    private lateinit var transactionManager: ITransactionManager

    private lateinit var saveDaoHelper: SaveItemsWithRemoteKeysDaoHelper<Identifiable>

    @Mock
    private lateinit var remoteKeyDao: RemoteKeyDao

    private lateinit var cleanDaoHelper: ClearAllItemsAndKeysDaoHelper<Identifiable>

    private val loadDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var pagingApiHelper: IPagingApiHelper<Identifiable>

    private lateinit var remoteMediatorDaoHelper: RemoteMediatorDaoHelper<Identifiable>

    private lateinit var remoteMediatorMock: BaseRemoteMediator<Identifiable>

    @Before
    fun setUp() {
        val daoHelperArguments = UseConstructor.withArguments(itemDao, remoteKeyDao, transactionManager)
        saveDaoHelper = mock(useConstructor = daoHelperArguments)
        cleanDaoHelper = mock(useConstructor = daoHelperArguments)
        remoteMediatorDaoHelper = mock(useConstructor = UseConstructor
            .withArguments(saveDaoHelper, remoteKeyDao, cleanDaoHelper)
        )

        remoteMediatorMock = BaseRemoteMediator(
            remoteMediatorDaoHelper, pagingApiHelper, loadDispatcher
        )
    }

    private fun getRefreshPagingState() : PagingState<Int, Identifiable> {
        return PagingState(
            listOf(),
            null,
            PagingConfig(10),
            10,
        )
    }

    @Test
    fun refreshPagingDataWhenMoreDataIsPresent() = runTest(loadDispatcher) {
        whenever(pagingApiHelper.load(any())).thenReturn(EmulatedData.characterList)

        val result = remoteMediatorMock.load(LoadType.REFRESH, getRefreshPagingState())

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Success::class.java)
        assertThat((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached).isFalse()
    }

    @Test
    fun refreshPagingDataWhenEndOfPagingIsReached() = runTest(loadDispatcher) {
        whenever(pagingApiHelper.load(any())).thenReturn(emptyList())

        val result = remoteMediatorMock.load(LoadType.REFRESH, getRefreshPagingState())

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Success::class.java)
        assertThat((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached).isTrue()
    }

    @Test
    fun refreshPagingDataWithNetworkError() = runTest(loadDispatcher) {
        whenever(pagingApiHelper.load(any())).doAnswer { throw IOException("Some exception") }

        val result = remoteMediatorMock.load(LoadType.REFRESH, getRefreshPagingState())

        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Error::class.java)
    }

}