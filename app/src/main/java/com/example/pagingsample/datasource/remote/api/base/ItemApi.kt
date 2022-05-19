package com.example.pagingsample.datasource.remote.api.base

import com.apollographql.apollo3.api.Query

interface ItemApi<T> {

    suspend fun getItemById(id: Long) : Query.Data?
}