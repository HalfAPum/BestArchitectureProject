package com.example.pagingsample.datasource.local.dao.base

import androidx.paging.PagingSource

interface BaseLoadAllDao<T : Any> {

    @JvmSuppressWildcards
    fun getAll(): PagingSource<Int, T>

}