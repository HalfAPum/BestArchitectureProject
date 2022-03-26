package com.example.pagingsample.model.api

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results")
    val characters: List<CharacterNetwork>
)

data class CharacterNetwork(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("location")
    val location: CharacterLocationNetwork,
    @SerializedName("image")
    val image: String,
    @SerializedName("created")
    val created: String,
)

data class CharacterLocationNetwork(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)