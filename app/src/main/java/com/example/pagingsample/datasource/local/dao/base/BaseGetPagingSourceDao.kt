package com.example.pagingsample.datasource.local.dao.base

import androidx.paging.PagingSource
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery

interface BaseGetPagingSourceDao<T : Any> {

    @RawQuery
    fun getPagingSource(query: SimpleSQLiteQuery): PagingSource<Int, T>

}