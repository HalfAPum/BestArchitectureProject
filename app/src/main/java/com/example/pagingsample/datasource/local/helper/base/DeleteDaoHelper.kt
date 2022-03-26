package com.example.pagingsample.datasource.local.helper.base

interface DeleteDaoHelper<T> {

    suspend fun delete(item: T)

}