package com.example.pagingsample.datasource.remote.api

import com.example.pagingsample.model.api.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharactersPaging(
        @Query("page") page: Int,
    ) : CharacterResponse

    companion object {

        const val CHARACTERS_PAGING_START = 1
        const val CHARACTERS_LOAD_SIZE = 20
    }
}