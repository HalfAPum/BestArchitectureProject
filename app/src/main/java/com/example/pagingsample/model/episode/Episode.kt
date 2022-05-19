package com.example.pagingsample.model.episode

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingsample.model.interfaces.Identifiable

@Entity
data class Episode(
    @PrimaryKey
    @ColumnInfo("id")
    override val id: Long,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("air_date")
    val airDate: String?,
) : Identifiable