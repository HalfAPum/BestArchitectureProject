package com.example.pagingsample.model.api

import com.google.gson.annotations.SerializedName

data class PassengersResponse(
    @SerializedName("data")
    val passengerNetworkList: List<PassengerNetwork>
)

data class PassengerNetwork(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("trips")
    val trips: Double,
    @SerializedName("airline")
    val airlinesNetwork: List<AirlineNetwork>,
)

data class AirlineNetwork(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("slogan")
    val slogan: String,
    @SerializedName("head_quaters")
    val headQuaters: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("established")
    val established: String,
)