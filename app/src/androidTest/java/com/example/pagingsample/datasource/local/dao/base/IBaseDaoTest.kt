package com.example.pagingsample.datasource.local.dao.base

import androidx.room.Dao
import com.example.pagingsample.datasource.local.AppDatabase

interface IBaseDaoTest<T, D : BaseDao<T>> {

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
     * Target database for tests.
     */
    var db: AppDatabase

    /**
     * Base actions functions for more readable test without repetitive code.
     */

    suspend fun insertSingleItem(item: T = singleItem) {
        dao.insert(item)
    }

    suspend fun insertItemList(items: List<T> = itemList) {
        dao.insert(items)
    }

    suspend fun getResult() = dao.getAll()


}