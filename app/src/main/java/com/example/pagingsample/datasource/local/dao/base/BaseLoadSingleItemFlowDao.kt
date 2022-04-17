package com.example.pagingsample.datasource.local.dao.base

import kotlinx.coroutines.flow.Flow

interface BaseLoadSingleItemFlowDao<T> {

    @JvmSuppressWildcards
    fun flow(id: String) : Flow<T>

}