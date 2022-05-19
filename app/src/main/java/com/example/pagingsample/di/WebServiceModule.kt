package com.example.pagingsample.di

import com.example.CharacterByIdQuery
import com.example.EpisodeByIdQuery
import com.example.LocationByIdQuery
import com.example.pagingsample.datasource.remote.api.query.*
import com.example.pagingsample.datasource.remote.api.query.base.BaseApiQuery
import com.example.pagingsample.datasource.remote.helper.ItemApiHelper
import com.example.pagingsample.datasource.remote.mapper.base.ItemMapper
import com.example.pagingsample.datasource.remote.mapper.item.CharacterDetailsMapper
import com.example.pagingsample.datasource.remote.mapper.item.EpisodeDetailsMapper
import com.example.pagingsample.datasource.remote.mapper.item.LocationDetailsMapper
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.character.CharacterWithDetails
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.episode.EpisodeWithDetails
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.model.location.LocationWithDetails
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WebServiceModule {

    @Binds
    abstract fun bindCharacterApiQuery(apiQuery: CharacterApiQuery): BaseApiQuery<Character>

    @Binds
    abstract fun bindLocationApiQuery(apiQuery: LocationApiQuery): BaseApiQuery<Location>

    @Binds
    abstract fun bindEpisodeApiQuery(apiQuery: EpisodeApiQuery): BaseApiQuery<Episode>

    @Binds
    abstract fun bindCharacterDetailsApiQuery(apiQuery: CharacterDetailsApiQuery): BaseApiQuery<CharacterWithDetails>

    @Binds
    abstract fun bindLocationDetailsApiQuery(apiQuery: LocationDetailsApiQuery): BaseApiQuery<LocationWithDetails>

    @Binds
    abstract fun bindEpisodeDetailsApiQuery(apiQuery: EpisodeDetailsApiQuery): BaseApiQuery<EpisodeWithDetails>

    @Binds
    abstract fun bindCharacterItemApiHelper(
        itemApiHelper: ItemApiHelper<CharacterByIdQuery.Data, CharacterWithDetails>
    ) : ItemApiHelper<*, CharacterWithDetails>

    @Binds
    abstract fun bindLocationItemApiHelper(
        itemApiHelper: ItemApiHelper<LocationByIdQuery.Data, LocationWithDetails>
    ) : ItemApiHelper<*, LocationWithDetails>

    @Binds
    abstract fun bindEpisodeItemApiHelper(
        itemApiHelper: ItemApiHelper<EpisodeByIdQuery.Data, EpisodeWithDetails>
    ) : ItemApiHelper<*, EpisodeWithDetails>


    @Binds
    abstract fun bindCharacterWithDetailsItemMapper(
        itemMapper: CharacterDetailsMapper
    ) : ItemMapper<CharacterByIdQuery.Data, CharacterWithDetails>

    @Binds
    abstract fun bindLocationWithDetailsItemMapper(
        itemMapper: LocationDetailsMapper
    ) : ItemMapper<LocationByIdQuery.Data, LocationWithDetails>

    @Binds
    abstract fun bindEpisodeWithDetailsItemMapper(
        itemMapper: EpisodeDetailsMapper
    ) : ItemMapper<EpisodeByIdQuery.Data, EpisodeWithDetails>

}