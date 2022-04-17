package com.example.pagingsample.datasource.remote.mapper.list

import com.example.LocationsPagingQuery
import com.example.pagingsample.datasource.remote.mapper.base.ListMapper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.location.Location
import javax.inject.Inject

class LocationListMapper @Inject constructor() : ListMapper<LocationsPagingQuery.Data, Location> {

    override fun map(
        serverData: LocationsPagingQuery.Data
    ) = serverData.map()

}