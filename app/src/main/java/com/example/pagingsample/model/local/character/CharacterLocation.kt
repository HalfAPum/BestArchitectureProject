package com.example.pagingsample.model.local.character

import androidx.room.ColumnInfo

data class CharacterLocation(
    @ColumnInfo("location_name")
    val name: String,
    @ColumnInfo("location_url")
    val url: String,
)