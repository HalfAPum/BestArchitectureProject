package com.example.pagingsample.di

import com.apollographql.apollo3.mockserver.MockServer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TestApolloClientModule {

    @Provides
    fun provideMockServer(): MockServer = MockServer()

}