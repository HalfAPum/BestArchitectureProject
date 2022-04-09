package com.example.pagingsample.di

import androidx.paging.PagingConfig
import com.example.pagingsample.datasource.remote.api.PagingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    @Provides
    fun providePagingLoadDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun providePagingConfig() = PagingConfig(
        pageSize = PagingApi.LOAD_SIZE,
    )

}