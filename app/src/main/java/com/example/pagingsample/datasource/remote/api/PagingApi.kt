package com.example.pagingsample.datasource.remote.api

import com.apollographql.apollo3.api.Query

interface PagingApi<T : Query.Data> {

    val pagingStart: Int
        get() = PAGING_START


    suspend fun getPagingItems(page: Int) : T?


    companion object {
        private const val PAGING_START = 1
        const val LOAD_SIZE = 20
    }
}