package com.example.pagingsample.datasource.remote.mapper.item

import com.example.LocationByIdQuery
import com.example.pagingsample.datasource.remote.mapper.base.ItemMapper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.location.LocationWithDetails
import org.koin.core.annotation.Factory

//import javax.inject.Inject
//import javax.inject.Singleton

//@Singleton
@Factory
class LocationDetailsMapper constructor() : ItemMapper<LocationByIdQuery.Data, LocationWithDetails> {

    override fun map(serverData: LocationByIdQuery.Data): LocationWithDetails? {
        return serverData.map()
    }

}