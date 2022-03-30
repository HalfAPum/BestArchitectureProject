package com.example.pagingsample.datasource.local.dao.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Update

interface BaseDao<T : Any> {

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: T)

    @Insert(onConflict = REPLACE)
    suspend fun insert(items: List<T>)


    @Update
    suspend fun update(item: T)

    @Update
    suspend fun update(items: List<T>)


    @Delete
    suspend fun delete(item: T)

    @Delete
    suspend fun delete(items: List<T>)

    @JvmSuppressWildcards
    suspend fun clear()

    @JvmSuppressWildcards
    suspend fun getAll(): List<T>

}