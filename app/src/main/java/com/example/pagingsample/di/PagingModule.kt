package com.example.pagingsample.di

import androidx.paging.PagingConfig
import com.example.pagingsample.datasource.remote.api.base.BaseApi
import com.example.pagingsample.datasource.remote.api.base.PagingApi
import com.example.pagingsample.datasource.remote.helper.IPagingApiHelper
import com.example.pagingsample.datasource.remote.helper.PagingApiHelper
import com.example.pagingsample.datasource.remote.mapper.list.CharacterListMapper
import com.example.pagingsample.datasource.remote.mapper.list.EpisodeListMapper
import com.example.pagingsample.datasource.remote.mapper.list.LocationListMapper
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.location.Location
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

    //TODO REMOVe
    @Provides
    fun provideCharacterPagingApiHelper(
        baseApi: BaseApi<Character>,
    ) : IPagingApiHelper<Character> = PagingApiHelper(baseApi, CharacterListMapper())

    @Provides
    fun provideLocationPagingApiHelper(
        baseApi: BaseApi<Location>,
    ) : IPagingApiHelper<Location> = PagingApiHelper(baseApi, LocationListMapper())

    @Provides
    fun provideEpisodePagingApiHelper(
        baseApi: BaseApi<Episode>,
    ) : IPagingApiHelper<Episode> = PagingApiHelper(baseApi, EpisodeListMapper())

}