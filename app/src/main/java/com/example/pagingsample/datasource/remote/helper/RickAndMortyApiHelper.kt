package com.example.pagingsample.datasource.remote.helper

import com.example.pagingsample.datasource.remote.api.RickAndMortyApi
import com.example.pagingsample.datasource.remote.helper.base.PagingApiHelper
import com.example.pagingsample.model.api.CharacterResponse
import com.example.pagingsample.model.api.map
import com.example.pagingsample.model.local.character.Character
import javax.inject.Inject

class CharacterPagingApiHelper @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
): PagingApiHelper<CharacterResponse, Character>() {

    override suspend fun loadFromServer(page: Int) = rickAndMortyApi.getCharactersPaging(page)

    override suspend fun CharacterResponse.mapServerData() = map()

}