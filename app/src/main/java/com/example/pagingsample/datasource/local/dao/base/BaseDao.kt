package com.example.pagingsample.datasource.local.dao.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery

interface BaseDao<T> {

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: T)

    @Insert(onConflict = REPLACE)
    suspend fun insertItems(items: List<T>)

    @Update
    suspend fun update(item: T)

    @Update
    suspend fun update(items: List<T>)


    @Delete
    suspend fun delete(item: T)

    @Delete
    suspend fun delete(items: List<T>)

    @RawQuery
    suspend fun clear(query: SimpleSQLiteQuery): Long

    @RawQuery
    suspend fun getAll(query: SimpleSQLiteQuery): List<T>

}