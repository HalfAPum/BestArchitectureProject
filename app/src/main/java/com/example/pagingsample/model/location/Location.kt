package com.example.pagingsample.model.location

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingsample.model.interfaces.Identifiable

@Entity
data class Location(
    @PrimaryKey
    @ColumnInfo("id")
    override val id: Long,
    @ColumnInfo("name")
    val name: String,
) : Identifiable