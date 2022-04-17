package com.example.pagingsample.datasource.remote.mapper.item

import com.example.LocationByIdQuery
import com.example.pagingsample.datasource.remote.mapper.base.ItemMapper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.location.LocationWithDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationDetailsMapper @Inject constructor() : ItemMapper<LocationByIdQuery.Data, LocationWithDetails> {

    override fun map(serverData: LocationByIdQuery.Data): LocationWithDetails? {
        return serverData.map()
    }

}