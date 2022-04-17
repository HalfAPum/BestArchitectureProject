package com.example.pagingsample.datasource.remote.api.query

import com.example.LocationByIdQuery
import com.example.LocationsPagingQuery
import com.example.pagingsample.datasource.remote.api.query.base.BaseApiQuery
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.model.location.LocationWithDetails
import com.example.pagingsample.utils.TypedQuery
import javax.inject.Inject

//TODO THINK ABOUT BETTER APPROACH

class LocationApiQuery @Inject constructor() : BaseApiQuery<Location> {

    override val pagingQuery: TypedQuery<Int> = { LocationsPagingQuery(it) }

    override val itemByIdQuery: TypedQuery<String> = { LocationByIdQuery(it) }

}

class LocationDetailsApiQuery @Inject constructor() : BaseApiQuery<LocationWithDetails> {

    override val pagingQuery: TypedQuery<Int> = { LocationsPagingQuery(it) }

    override val itemByIdQuery: TypedQuery<String> = { LocationByIdQuery(it) }

}