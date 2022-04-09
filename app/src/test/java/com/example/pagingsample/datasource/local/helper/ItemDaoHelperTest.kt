package com.example.pagingsample.datasource.local.helper

import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.utils.EmulatedData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.UseConstructor
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ItemDaoHelperTest {

    @Mock
    lateinit var pagingDao: BaseGetPagingSourceDao<Any>

    @Mock
    lateinit var itemDao: BaseDao<Any>

    @Mock
    lateinit var remoteKeyDao: RemoteKeyDao

    private val mockTransactionManager = MockTransactionManager()

    @Test
    fun `save valid items and remoteKeys verify functions usage`() = runTest {
        val daoHelper = getSaveItemsWithRemoteKeysDaoHelper()
        val items = EmulatedData.characterList
        val remoteKeys = EmulatedData.remoteKeyList
        daoHelper.save(items, remoteKeys)

        verify(itemDao).insertItems(items)
        verify(remoteKeyDao).insertItems(remoteKeys)
    }

    @Test
    fun `save empty data verify all functions triggered`() = runTest {
        val daoHelper = getSaveItemsWithRemoteKeysDaoHelper()
        daoHelper.save(emptyList(), emptyList())

        verify(itemDao).insertItems(emptyList())
        verify(remoteKeyDao).insertItems(emptyList())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `save items and remoteKeys with different data size throws exception`() = runTest {
        val daoHelper = getSaveItemsWithRemoteKeysDaoHelper()
        daoHelper.save(EmulatedData.characterList, emptyList())
    }

    private fun getSaveItemsWithRemoteKeysDaoHelper() : SaveItemsWithRemoteKeysDaoHelper<Any> =
        mock(useConstructor = UseConstructor.withArguments(
            itemDao, remoteKeyDao, mockTransactionManager
        ))

    @Test
    fun `clear item dao and remoteKey dao`() = runTest {
        val clearDaoHelper = ClearAllItemsAndKeysDaoHelper(itemDao, remoteKeyDao, mockTransactionManager)
        clearDaoHelper.clearTables()

        verify(itemDao).clear()
        verify(remoteKeyDao).clear()
    }

    @Test
    fun `get paging source`() = runTest {
        val pagingDaoHelper: GetPagingSourceDaoHelper<Any> = mock(
            useConstructor = UseConstructor.withArguments(pagingDao)
        )
        pagingDaoHelper.getPagingSource()

        verify(pagingDao).getPagingSource()
    }

}