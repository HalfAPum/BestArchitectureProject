package com.example.pagingsample.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApolloClientModule {

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

    private const val RICK_AND_MORTY_GRAPHQL_BASE_URL = "https://rickandmortyapi.com/graphql"
}