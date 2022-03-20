package com.example.pagingsample.model.api

import com.google.gson.annotations.SerializedName

data class PassengersResponse(
    @SerializedName("data")
    val data: List<Passenger>
)

data class Passenger(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("trips")
    val trips: Int,
    @SerializedName("airline")
    val airlines: List<Airline>,
)

data class Airline(
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