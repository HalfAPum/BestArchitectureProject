package com.example.pagingsample.datasource.remote.helper

import com.apollographql.apollo3.api.ApolloResponse
import com.example.CharactersPagingQuery
import com.example.pagingsample.datasource.remote.api.CharacterApi
import com.example.pagingsample.datasource.remote.helper.base.PagingApiHelper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.local.character.Character
import javax.inject.Inject

class CharacterPagingApiHelper @Inject constructor(
    private val characterApi: CharacterApi,
): PagingApiHelper<ApolloResponse<CharactersPagingQuery.Data>, Character>() {

    override suspend fun loadFromServer(page: Int) = characterApi.getCharactersPaging(page)

    override suspend fun ApolloResponse<CharactersPagingQuery.Data>.mapServerData() = map()

}