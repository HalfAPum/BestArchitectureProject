package com.example.pagingsample.di

import com.example.CharactersPagingQuery
import com.example.EpisodesPagingQuery
import com.example.LocationsPagingQuery
import com.example.pagingsample.datasource.remote.api.BaseApi
import com.example.pagingsample.datasource.remote.helper.IPagingApiHelper
import com.example.pagingsample.datasource.remote.helper.PagingApiHelper
import com.example.pagingsample.datasource.remote.mapper.list.CharacterListMapper
import com.example.pagingsample.datasource.remote.mapper.list.EpisodeListMapper
import com.example.pagingsample.datasource.remote.mapper.list.LocationListMapper
import com.example.pagingsample.model.Character
import com.example.pagingsample.model.Episode
import com.example.pagingsample.model.Location
import com.example.pagingsample.utils.PagingQueryCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {

    @Provides
    fun provideCharacterPagingApiHelper(
        baseApi: BaseApi<CharactersPagingQuery.Data>,
    ) : IPagingApiHelper<Character> = PagingApiHelper(baseApi, CharacterListMapper)

    @Provides
    fun provideCharacterPagingQuery(): PagingQueryCallback<CharactersPagingQuery.Data> = {
        CharactersPagingQuery(it)
    }

    @Provides
    fun provideLocationPagingApiHelper(
        baseApi: BaseApi<LocationsPagingQuery.Data>,
    ) : IPagingApiHelper<Location> = PagingApiHelper(baseApi, LocationListMapper)

    @Provides
    fun provideLocationPagingQuery(): PagingQueryCallback<LocationsPagingQuery.Data> = {
        LocationsPagingQuery(it)
    }

    @Provides
    fun provideEpisodePagingApiHelper(
        baseApi: BaseApi<EpisodesPagingQuery.Data>,
    ) : IPagingApiHelper<Episode> = PagingApiHelper(baseApi, EpisodeListMapper)

    @Provides
    fun provideEpisodePagingQuery(): PagingQueryCallback<EpisodesPagingQuery.Data> = {
        EpisodesPagingQuery(it)
    }

}