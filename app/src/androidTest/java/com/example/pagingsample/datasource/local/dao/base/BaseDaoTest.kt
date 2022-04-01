package com.example.pagingsample.datasource.local.dao.base

import androidx.annotation.CallSuper
import androidx.room.*
import androidx.test.core.app.ApplicationProvider
import com.example.pagingsample.datasource.local.AppDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseDaoTest<T : Any, D : BaseDao<T>> : IBaseDaoTest<T, D> {

    override lateinit var dao: D
    override lateinit var db: AppDatabase

    @Before
    @CallSuper
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).build()

        initDao()
    }

    abstract fun initDao()

    @After
    @CallSuper
    fun teardown() = db.close()

    /**
     * Validate test data tests.
     */
    @Test
    fun checkItemListDoesNotContainSingleItem() {
        assertThat(itemList).doesNotContain(singleItem)
    }

    @Test
    fun checkItemListSize() {
        assertThat(itemList.size > MINIMAL_TEST_LIST_SIZE).isTrue()
    }


    /**
     * Validate [Insert] functions tests.
     */
    @Test
    fun testInsertItem() = runTest {
        insertSingleItem()

        assertThat(getResult().first()).isEqualTo(singleItem)
    }

    @Test
    fun testInsertItemsTest() = runTest {
        insertItemList(itemList)

        assertThat(getResult()).isEqualTo(itemList)
    }

    /**
     * Test can fail with 2 reasons:
     * 1) Transformed item does not have same PK as original item;
     * 2) Real insert strategy is different from [OnConflictStrategy.REPLACE].
     */
    @Test
    fun checkInsertOnReplaceStrategy() = runTest {
        insertSingleItem(singleItem)
        val transformedItem = singleItem.transform()
        insertSingleItem(transformedItem)

        assertThat(getResult().first()).isEqualTo(transformedItem)
    }

    /**
     * Input and result [T] should be different with same PK.
     */
    abstract fun T.transform() : T

    @Test
    fun checkTransformedItemIsNotTheSameItem() {
        val transformedItem = singleItem.transform()
        assertThat(transformedItem).isNotEqualTo(singleItem)
    }

    /**
     * Validate [Update] functions tests
     */

    @Test
    fun updateSingleItem() = runTest {
        insertSingleItem(singleItem)
        val transformedItem = singleItem.transform()
        dao.update(transformedItem)

        assertThat(getResult().first()).isEqualTo(transformedItem)
    }

    @Test
    fun updateItems() = runTest {
        insertItemList(itemList)
        val transformedList = itemList.map { it.transform() }
        dao.update(transformedList)

        assertThat(getResult()).isEqualTo(transformedList)
    }

    @Test
    fun updateSameItem() = runTest {
        insertItemList(itemList)
        dao.update(itemList)

        assertThat(getResult()).isEqualTo(itemList)
    }

    @Test
    fun updateNotExistingItem()= runTest {
        dao.update(singleItem)

        assertThat(getResult()).isEmpty()
    }

    /**
     * Validate [Delete] functions tests.
     */

    @Test
    fun deleteItem() = runTest {
        insertSingleItem(singleItem)
        dao.delete(singleItem)

        assertThat(getResult()).doesNotContain(singleItem)
    }

    @Test
    fun deleteItems() = runTest {
        insertItemList(itemList)
        dao.delete(itemList)

        assertThat(getResult()).isEmpty()
    }

    @Test
    fun deleteNotExistingItem() = runTest {
        dao.delete(singleItem)

        assertThat(getResult()).isEmpty()
    }

    @Test
    fun clearAll() = runTest {
        insertItemList()
        dao.clear()

        assertThat(getResult()).isEmpty()
    }

    @Test
    fun clearEmptyTable() = runTest {
        dao.clear()

        assertThat(getResult()).isEmpty()
    }

    companion object {
        private const val MINIMAL_TEST_LIST_SIZE = 2
    }

}