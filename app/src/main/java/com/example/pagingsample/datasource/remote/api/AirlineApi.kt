package com.example.pagingsample.datasource.remote.api

import com.example.pagingsample.model.api.PassengersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AirlineApi {

    @GET("passenger")
    suspend fun getPassengersPaging(
        @Query("page") page: Int,
        @Query("size") size: Int = PASSENGERS_LOAD_SIZE,
    ): PassengersResponse

    companion object {

        const val PASSENGERS_PAGING_START = 0
        const val PASSENGERS_LOAD_SIZE = 20
        const val PASSENGERS_INITIAL_LOAD_SIZE = PASSENGERS_LOAD_SIZE * 3
    }
}