package com.example.pagingsample.datasource.paging.api

import com.example.pagingsample.datasource.remote.api.AirlineApi
import com.example.pagingsample.model.api.PassengersResponse
import com.example.pagingsample.model.api.toPassengerNetworkList
import com.example.pagingsample.model.local.Passenger
import okio.IOException

class MockAirlineApi(
    private val mockPassengers: List<Passenger>,
    private var throwError: Boolean = false,
) : AirlineApi {


    override suspend fun getPassengersPaging(page: Int, size: Int): PassengersResponse {
        if (throwError) throw IOException("Exception for paging")
        return PassengersResponse(mockPassengers.toPassengerNetworkList())
    }
}