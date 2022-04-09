package com.example.pagingsample.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {

    @Singleton
    @Provides
    @Named(OKHTTP_CLIENT)
    fun provideOkHttpClient(
    ) = OkHttpClient.Builder()
        .build()

    @Singleton
    @Provides
    @Named(RICK_AND_MORTY_APOLLO_CLIENT)
    fun provideRickAndMortyApolloClient(
        @Named(OKHTTP_CLIENT) okHttpClient: OkHttpClient
    ) : ApolloClient = ApolloClient.Builder()
        .serverUrl(RICK_AND_MORTY_GRAPHQL_BASE_URL)
        .okHttpClient(okHttpClient)
        .build()

    private const val OKHTTP_CLIENT = "OKHTTP_CLIENT"

    private const val RICK_AND_MORTY_GRAPHQL_BASE_URL = "https://rickandmortyapi.com/graphql"
    const val RICK_AND_MORTY_APOLLO_CLIENT = "RICK_AND_MORTY_GRAPHQL"

}