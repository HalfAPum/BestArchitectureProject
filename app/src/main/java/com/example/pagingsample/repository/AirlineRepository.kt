package com.example.pagingsample.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagingsample.datasource.local.helper.GetPagingSourceDaoHelper
import com.example.pagingsample.datasource.paging.PassengerRemoteMediator
import com.example.pagingsample.datasource.remote.api.AirlineApi
import com.example.pagingsample.model.local.Passenger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AirlineRepository @Inject constructor(
    private val passengerRemoteMediator: PassengerRemoteMediator,
    private val getPagingSourceDaoHelper: GetPagingSourceDaoHelper<Passenger>,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getPassengersPagingData() = Pager(
        config = PagingConfig(
            pageSize = AirlineApi.PASSENGERS_LOAD_SIZE,
            initialLoadSize = AirlineApi.PASSENGERS_INITIAL_LOAD_SIZE,
        ),
        remoteMediator = passengerRemoteMediator,
        pagingSourceFactory = { getPagingSourceDaoHelper.getPagingSource() },
    ).flow

}