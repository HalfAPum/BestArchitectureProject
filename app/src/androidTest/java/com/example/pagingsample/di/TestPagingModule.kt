package com.example.pagingsample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PagingModule::class]
)
object TestPagingModule {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    fun providePagingLoadDispatcher(): CoroutineDispatcher = StandardTestDispatcher()

}