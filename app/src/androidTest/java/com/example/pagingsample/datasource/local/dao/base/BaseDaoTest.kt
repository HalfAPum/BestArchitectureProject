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
abstract class BaseDaoTest<T : Any> {

    /**
     * Data for tests.
     * [itemList] should not contain [singleItem].
     */
    abstract val singleItem: T
    abstract val itemList: List<T>

    protected lateinit var db: AppDatabase
    protected lateinit var dao: BaseDao<T>

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
    fun insertSingleItem() = runTest {
        dao.insert(singleItem)
        val result = dao.getAll()

        assertThat(singleItem).isEqualTo(result.first())
    }

    @Test
    fun insertItems() = runTest {
        dao.insert(itemList)
        val result = dao.getAll()

        assertThat(itemList).isEqualTo(result)
    }

    /**
     * Test can fail with 2 reasons:
     * 1) Transformed item does not have same PK as original item;
     * 2) Real insert strategy is different from [OnConflictStrategy.REPLACE].
     */
    @Test
    fun checkInsertOnReplaceStrategy() = runTest {
        dao.insert(singleItem)
        val transformedItem = singleItem.transform()
        dao.insert(transformedItem)
        val result = dao.getAll().first()

        assertThat(result).isEqualTo(transformedItem)
    }

    /**
     * Input and result [T] should be different with same PK.
     */
    abstract fun T.transform() : T

    @Test
    fun checkTransformedItemIsNotTheSameItem() {
        val transformedItem = singleItem.transform()
        assertThat(singleItem).isNotEqualTo(transformedItem)
    }

    /**
     * Validate [Update] functions tests
     */

    @Test
    fun updateSingleItem() = runTest {
        dao.insert(singleItem)
        val transformedItem = singleItem.transform()
        dao.update(transformedItem)
        val result = dao.getAll()

        assertThat(result.first()).isEqualTo(transformedItem)
    }

    @Test
    fun updateItems() = runTest {
        dao.insert(itemList)
        val transformedList = itemList.map { it.transform() }
        dao.update(transformedList)
        val result = dao.getAll()

        assertThat(result).isEqualTo(transformedList)
    }

    @Test
    fun updateSameItem() = runTest {
        dao.insert(itemList)
        dao.update(itemList)
        val result = dao.getAll()

        assertThat(result).isEqualTo(itemList)
    }

    @Test()
    fun updateNotExistingItem()= runTest {
        dao.update(singleItem)
        val result = dao.getAll()

        assertThat(result).isEmpty()
    }

    /**
     * Validate [Delete] functions tests.
     */

    @Test
    fun deleteItem() = runTest {
        dao.insert(itemList)
        dao.delete(singleItem)
        val result = dao.getAll()

        assertThat(result).doesNotContain(singleItem)
    }

    @Test
    fun deleteItems() = runTest {
        dao.insert(itemList)
        dao.delete(itemList)
        val result = dao.getAll()

        assertThat(result).isEmpty()
    }

    @Test
    fun deleteNotExistingItem() = runTest {
        dao.delete(singleItem)
        val result = dao.getAll()

        assertThat(result).isEmpty()
    }

    @Test
    fun clearAll() = runTest {
        dao.insert(itemList)
        dao.clear()
        val result = dao.getAll()

        assertThat(result).isEmpty()
    }

    @Test
    fun clearEmptyTable() = runTest {
        dao.clear()
        val result = dao.getAll()

        assertThat(result).isEmpty()
    }

    companion object {
        private const val MINIMAL_TEST_LIST_SIZE = 2
    }

}