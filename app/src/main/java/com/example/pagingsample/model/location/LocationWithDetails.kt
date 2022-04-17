package com.example.pagingsample.model.location

import androidx.room.Embedded
import androidx.room.Relation

data class LocationWithDetails(
    @Embedded
    val location: Location,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val details: LocationDetails,
)