package com.example.pagingsample.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingsample.model.local.interfaces.Identifiable

@Entity
data class Passenger(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name  = "trips")
    val trips: Double,
) : Identifiable