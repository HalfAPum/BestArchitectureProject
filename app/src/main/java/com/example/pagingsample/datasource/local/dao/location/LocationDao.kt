package com.example.pagingsample.datasource.local.dao.location

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.location.Location

@Dao
interface LocationDao : BaseDao<Location>, BaseGetPagingSourceDao<Location> {

    @RawQuery(observedEntities = [Location::class])
    override fun getPagingSource(query: SimpleSQLiteQuery): PagingSource<Int, Location>

}