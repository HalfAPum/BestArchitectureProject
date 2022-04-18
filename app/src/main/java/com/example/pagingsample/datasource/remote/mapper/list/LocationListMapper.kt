package com.example.pagingsample.datasource.remote.mapper.list

import com.example.LocationsPagingQuery
import com.example.pagingsample.datasource.remote.mapper.base.ListMapper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.location.Location
import org.koin.core.annotation.Factory

@Factory
class LocationListMapper constructor() : ListMapper<LocationsPagingQuery.Data, Location> {

    override fun map(
        serverData: LocationsPagingQuery.Data
    ) = serverData.map()

}