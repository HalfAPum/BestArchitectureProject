package com.example.pagingsample.datasource.paging.base

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator

@OptIn(ExperimentalPagingApi::class)
sealed class LoadTypeResult {

    class PageResult(val page: Int): LoadTypeResult()

    class MediatorSuccessResult(
        val success: RemoteMediator.MediatorResult.Success
    ): LoadTypeResult()

}