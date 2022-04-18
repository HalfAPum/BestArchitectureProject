package com.example.pagingsample.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import org.koin.dsl.module

val apolloClientModule = module {
    single { OkHttpClient.Builder().build() }

    single {
        ApolloClient.Builder()
            .serverUrl(RICK_AND_MORTY_GRAPHQL_BASE_URL)
            .okHttpClient(get())
            .build()
    }
}

private const val RICK_AND_MORTY_GRAPHQL_BASE_URL = "https://rickandmortyapi.com/graphql"