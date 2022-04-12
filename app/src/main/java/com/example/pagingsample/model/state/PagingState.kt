package com.example.pagingsample.model.state

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class PagingState<T : Any>(
    val data: Flow<PagingData<T>>
)