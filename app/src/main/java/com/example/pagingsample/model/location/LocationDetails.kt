package com.example.pagingsample.model.location

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Location::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class LocationDetails(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("type")
    val type: String?,
    @ColumnInfo("dimension")
    val dimension: String?,
)