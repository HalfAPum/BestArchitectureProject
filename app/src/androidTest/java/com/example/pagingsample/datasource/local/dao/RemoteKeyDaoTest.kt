package com.example.pagingsample.datasource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pagingsample.datasource.local.dao.base.BaseDaoTest
import com.example.pagingsample.model.RemoteKey
import com.example.pagingsample.utils.EmulatedData
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@SmallTest
class RemoteKeyDaoTest : BaseDaoTest<RemoteKey, RemoteKeyDao>() {

    override val singleItem = EmulatedData.remoteKey
    override val itemList = EmulatedData.remoteKeyList

    override fun RemoteKey.transform() = copy(prevKey = -1000)

    @Test
    fun getItemById() = runTest {
        insertItemList(itemList)
        val dataToFind = itemList.first()
        val result = dao.getById(dataToFind.id)

        assertThat(result).isEqualTo(dataToFind)
    }

    @Test
    fun getNotExistingItemById() = runTest {
        val notExistingItem = singleItem
        val result = dao.getById(notExistingItem.id)

        assertThat(result).isEqualTo(null)
    }

}