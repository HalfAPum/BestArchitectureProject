package com.example.pagingsample.datasource.remote.mapper.item

import com.example.EpisodeByIdQuery
import com.example.pagingsample.datasource.remote.mapper.base.ItemMapper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.episode.EpisodeWithDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodeDetailsMapper @Inject constructor() : ItemMapper<EpisodeByIdQuery.Data, EpisodeWithDetails> {

    override fun map(serverData: EpisodeByIdQuery.Data): EpisodeWithDetails? {
        return serverData.map()
    }

}