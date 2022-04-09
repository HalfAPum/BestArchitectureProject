package com.example.pagingsample.datasource.remote.helper

interface IPagingApiHelper<RESULT : Any> {

    val pagingStart: Int

    suspend fun load(page: Int): List<RESULT>

}