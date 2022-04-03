package com.example.pagingsample.datasource.local.helper.base

import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.IBaseDaoTest
import com.example.pagingsample.datasource.local.helper.ClearAllItemsAndKeysDaoHelper
import com.example.pagingsample.datasource.local.helper.SaveItemsWithRemoteKeysDaoHelper
import com.example.pagingsample.model.local.RemoteKey
import com.example.pagingsample.utils.EmulatedData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
interface IBaseDaoWithRemoteKeysTest<T : Any, D : BaseDao<T>> : IBaseDaoTest<T, D> {

    val remoteKeyList: List<RemoteKey>
        get() = EmulatedData.remoteKeyList

    var remoteKeyDao: RemoteKeyDao

    fun saveItemsWithRemoteKeys(
        inputItemList: List<T>,
        inputRemoteKeyList: List<RemoteKey>
    ) = runTest {
        SaveItemsWithRemoteKeysDaoHelper(
            dao, remoteKeyDao, transactionManager
        ).save(inputItemList, inputRemoteKeyList)

        val resultItemList = dao.getAll()
        val resultRemoteKeyList = remoteKeyDao.getAll()

        assertThat(resultItemList).isEqualTo(inputItemList)
        assertThat(resultRemoteKeyList).isEqualTo(inputRemoteKeyList)
    }

    @Test
    fun saveItemsWithRemoteKeysRealData() {
        saveItemsWithRemoteKeys(itemList, remoteKeyList)
    }

    @Test
    fun saveItemsWithRemoteKeysEmptyData() {
        saveItemsWithRemoteKeys(emptyList(), emptyList())
    }

    @Test
    fun saveRealItemsWithEmptyRemoteKeys() {
        saveItemsWithRemoteKeys(itemList, emptyList())
    }

    @Test
    fun saveEmptyItemsWithRealRemoteKeys() {
        saveItemsWithRemoteKeys(emptyList(), remoteKeyList)
    }

    fun clearItemsWithRemoteKeys(
        inputItemList: List<T>,
        inputRemoteKeyList: List<RemoteKey>
    ) = runTest {
        SaveItemsWithRemoteKeysDaoHelper(
            dao, remoteKeyDao, transactionManager
        ).save(inputItemList, inputRemoteKeyList)

        ClearAllItemsAndKeysDaoHelper(
            dao, remoteKeyDao, transactionManager
        ).clearTables()

        val resultItemList = dao.getAll()
        val resultRemoteKeyList = remoteKeyDao.getAll()

        assertThat(resultItemList).isEmpty()
        assertThat(resultRemoteKeyList).isEmpty()
    }

    @Test
    fun clearItemsWithRemoteKeysRealData() {
        clearItemsWithRemoteKeys(itemList, remoteKeyList)
    }

    @Test
    fun clearItemsWithRemoteKeysEmptyData() {
        clearItemsWithRemoteKeys(emptyList(), emptyList())
    }

}