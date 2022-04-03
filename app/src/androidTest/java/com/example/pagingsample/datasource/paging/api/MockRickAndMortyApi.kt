package com.example.pagingsample.datasource.paging.api

import com.example.pagingsample.datasource.remote.api.RickAndMortyApi
import com.example.pagingsample.model.api.CharacterResponse
import com.example.pagingsample.model.api.toCharacterNetworkList
import com.example.pagingsample.model.local.character.Character
import okio.IOException

class MockRickAndMortyApi(
    private val mockCharacters: List<Character>,
    private var throwError: Boolean = false,
) : RickAndMortyApi {

    override suspend fun getCharactersPaging(page: Int): CharacterResponse {
        if (throwError) throw IOException("Exception for paging")
        return CharacterResponse(mockCharacters.toCharacterNetworkList())
    }

}