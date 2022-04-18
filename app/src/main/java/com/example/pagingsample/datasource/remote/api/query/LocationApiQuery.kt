package com.example.pagingsample.datasource.remote.api.query

import com.example.LocationByIdQuery
import com.example.LocationsPagingQuery
import com.example.pagingsample.datasource.remote.api.query.base.BaseApiQuery
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.model.location.LocationWithDetails
import com.example.pagingsample.utils.TypedQuery
import org.koin.core.annotation.Factory

//TODO THINK ABOUT BETTER APPROACH

@Factory
class LocationApiQuery constructor() : BaseApiQuery<Location> {

    override val pagingQuery: TypedQuery<Int> = { LocationsPagingQuery(it) }

    override val itemByIdQuery: TypedQuery<Long> = { LocationByIdQuery(it.toString()) }

}

@Factory
class LocationDetailsApiQuery constructor() : BaseApiQuery<LocationWithDetails> {

    override val pagingQuery: TypedQuery<Int> = { LocationsPagingQuery(it) }

    override val itemByIdQuery: TypedQuery<Long> = { LocationByIdQuery(it.toString()) }

}