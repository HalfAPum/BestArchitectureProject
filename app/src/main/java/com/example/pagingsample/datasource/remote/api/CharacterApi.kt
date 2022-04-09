package com.example.pagingsample.datasource.remote.api

import com.apollographql.apollo3.ApolloClient
import com.example.CharactersPagingQuery
import com.example.pagingsample.di.WebServiceModule
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class CharacterApi @Inject constructor(
    @Named(WebServiceModule.RICK_AND_MORTY_APOLLO_CLIENT)
    private val apolloClient: ApolloClient
) {

    suspend fun getCharactersPaging(page: Int) =
        apolloClient.query(CharactersPagingQuery(page)).execute()

    companion object {
        const val CHARACTERS_PAGING_START = 1
        const val CHARACTERS_LOAD_SIZE = 20
    }
}