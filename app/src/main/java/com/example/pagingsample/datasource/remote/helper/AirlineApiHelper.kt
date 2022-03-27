package com.example.pagingsample.datasource.remote.helper

import com.example.pagingsample.datasource.remote.api.AirlineApi
import com.example.pagingsample.datasource.remote.helper.base.PagingApiHelper
import com.example.pagingsample.model.api.PassengersResponse
import com.example.pagingsample.model.api.map
import com.example.pagingsample.model.local.Passenger
import javax.inject.Inject

class PassengerPagingApiHelper @Inject constructor(
    private val airlineApi: AirlineApi,
): PagingApiHelper<PassengersResponse, Passenger>() {

    override suspend fun loadFromServer(page: Int) = airlineApi.getPassengersPaging(page)

    override suspend fun PassengersResponse.mapServerData() = map()

}