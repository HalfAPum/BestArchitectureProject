package com.example.pagingsample.datasource.remote.api.query

import com.example.EpisodeByIdQuery
import com.example.EpisodesPagingQuery
import com.example.pagingsample.datasource.remote.api.query.base.BaseApiQuery
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.episode.EpisodeWithDetails
import com.example.pagingsample.utils.TypedQuery
import org.koin.core.annotation.Factory

@Factory
class EpisodeApiQuery constructor() : BaseApiQuery<Episode> {

    override val pagingQuery: TypedQuery<Int> = { EpisodesPagingQuery(it) }

    override val itemByIdQuery: TypedQuery<Long> = { EpisodeByIdQuery(it.toString()) }

}

@Factory
class EpisodeDetailsApiQuery constructor() : BaseApiQuery<EpisodeWithDetails> {

    override val pagingQuery: TypedQuery<Int> = { EpisodesPagingQuery(it) }

    override val itemByIdQuery: TypedQuery<Long> = { EpisodeByIdQuery(it.toString()) }

}