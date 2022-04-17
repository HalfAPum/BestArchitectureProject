package com.example.pagingsample.datasource.local.dao.location

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.location.Location

@Dao
interface LocationDao : BaseDao<Location>, BaseGetPagingSourceDao<Location> {

    @Query("DELETE FROM Location")
    @JvmSuppressWildcards
    override suspend fun clear()

    @Query("SELECT * FROM Location")
    @JvmSuppressWildcards
    override fun getPagingSource(): PagingSource<Int, Location>

    @Query("SELECT * FROM Location")
    @JvmSuppressWildcards
    override suspend fun getAll(): List<Location>

}