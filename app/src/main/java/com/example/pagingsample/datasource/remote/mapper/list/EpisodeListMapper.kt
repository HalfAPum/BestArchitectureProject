package com.example.pagingsample.datasource.remote.mapper.list

import com.example.EpisodesPagingQuery
import com.example.pagingsample.datasource.remote.mapper.base.ListMapper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.episode.Episode
import org.koin.core.annotation.Factory

@Factory
class EpisodeListMapper constructor() : ListMapper<EpisodesPagingQuery.Data, Episode> {

    override fun map(
        serverData: EpisodesPagingQuery.Data
    ) = serverData.map()

}