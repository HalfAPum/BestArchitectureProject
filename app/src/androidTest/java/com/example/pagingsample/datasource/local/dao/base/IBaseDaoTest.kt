package com.example.pagingsample.datasource.local.dao.base

import androidx.room.Dao
import com.example.pagingsample.InstrumentationHiltTest
import com.google.common.truth.Truth
import org.junit.Test

interface IBaseDaoTest<T, D : BaseDao<T>> : DatabaseTest, InstrumentationHiltTest {

    /**
     * Data for tests.
     * [itemList] should not contain [singleItem].
     */
    val singleItem: T
    val itemList: List<T>

    /**
     * Target [Dao] for tests.
     */
    var dao: D

    /**
     * Validate test data tests.
     */
    @Test
    fun checkItemListDoesNotContainSingleItem() {
        Truth.assertThat(itemList).doesNotContain(singleItem)
    }

    @Test
    fun checkItemListSize() {
        Truth.assertThat(itemList.size > MINIMAL_TEST_LIST_SIZE).isTrue()
    }

    /**
     * Transforms given item to different one.
     * Input and result [T] should be different with same PK.
     */
    fun T.transform() : T

    @Test
    fun checkTransformedItemIsNotTheSameItem() {
        val transformedItem = singleItem.transform()
        Truth.assertThat(transformedItem).isNotEqualTo(singleItem)
    }

    /**
     * Base actions functions for more readable test without repetitive code.
     */

    suspend fun insertSingleItem(item: T = singleItem) {
        dao.insert(item)
    }

    suspend fun insertItemList(items: List<T> = itemList) {
        dao.insertItems(items)
    }

    suspend fun getResult() = dao.getAll()

    companion object {
        private const val MINIMAL_TEST_LIST_SIZE = 2
    }

}