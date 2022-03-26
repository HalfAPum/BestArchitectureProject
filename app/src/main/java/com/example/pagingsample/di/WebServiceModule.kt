package com.example.pagingsample.di

import com.example.pagingsample.datasource.remote.api.AirlineApi
import com.example.pagingsample.datasource.remote.api.RickAndMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = Level.BASIC
    }

    @Singleton
    @Provides
    fun provideLoggingOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    @Named(AIRLINE_RETROFIT)
    fun provideAirlineRetrofitInstance(
        loggingOkHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(AIRLINE_BASE_URL)
        .client(loggingOkHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideAirlineApi(
        @Named(AIRLINE_RETROFIT) retrofit: Retrofit
    ): AirlineApi = retrofit.create(AirlineApi::class.java)

    @Singleton
    @Provides
    @Named(RICK_AND_MORTY_RETROFIT)
    fun provideRickAndMortyRetrofitInstance(
        loggingOkHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(RICK_AND_MORTY_BASE_URL)
        .client(loggingOkHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideRickAndMortyApi(
        @Named(RICK_AND_MORTY_RETROFIT) retrofit: Retrofit
    ): RickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)

    const val AIRLINE_BASE_URL = "https://api.instantwebtools.net/v1/"
    const val RICK_AND_MORTY_BASE_URL = "https://rickandmortyapi.com/api/"

    const val AIRLINE_RETROFIT = "AIRLINE RETROFIT"
    const val RICK_AND_MORTY_RETROFIT = "RICK AND MORTY RETROFIT"
}