package com.example.pagingsample.datasource.local.dao.base

import androidx.paging.PagingSource

interface BaseGetPagingSourceDao<T : Any> {

    @JvmSuppressWildcards
    fun getPagingSource(): PagingSource<Int, T>

}