package com.example.pagingsample.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.CharactersPagingQuery
import com.example.pagingsample.datasource.remote.api.BaseApi
import com.example.pagingsample.datasource.remote.helper.PagingApiHelper
import com.example.pagingsample.datasource.remote.mapper.list.CharacterListMapper
import com.example.pagingsample.model.local.character.Character
import com.example.pagingsample.utils.PagingQueryCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
    ) = OkHttpClient.Builder()
        .build()

    @Singleton
    @Provides
    fun provideRickAndMortyApolloClient(
        okHttpClient: OkHttpClient
    ) : ApolloClient = ApolloClient.Builder()
        .serverUrl(RICK_AND_MORTY_GRAPHQL_BASE_URL)
        .okHttpClient(okHttpClient)
        .build()

    @Provides
    fun provideCharacterPagingApiHelper(
        baseApi: BaseApi<CharactersPagingQuery.Data>,
    ) : PagingApiHelper<*, Character> = PagingApiHelper(baseApi, CharacterListMapper)

    @Provides
    fun provideCharacterPagingQuery(): PagingQueryCallback<CharactersPagingQuery.Data> = {
        CharactersPagingQuery(it)
    }


    private const val RICK_AND_MORTY_GRAPHQL_BASE_URL = "https://rickandmortyapi.com/graphql"
}