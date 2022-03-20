package com.example.pagingsample.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagingsample.datasource.paging.PassengerPagingSource
import com.example.pagingsample.datasource.remote.api.AirlineApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirplaneRepository @Inject constructor(
    private val passengerPagingSource: PassengerPagingSource
) {

    fun getPassengersPagingData() = Pager(
        config = PagingConfig(
            pageSize = AirlineApi.PASSENGERS_LOAD_SIZE,
            enablePlaceholders = false,
            initialLoadSize = AirlineApi.PASSENGERS_INITIAL_LOAD_SIZE,
        ),
        pagingSourceFactory = { passengerPagingSource },
    ).flow

}