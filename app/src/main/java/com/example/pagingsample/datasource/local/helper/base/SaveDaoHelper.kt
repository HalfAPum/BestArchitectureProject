package com.example.pagingsample.datasource.local.helper.base

interface SaveDaoHelper<T> {

    suspend fun save(item: T)

}