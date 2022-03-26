package com.example.pagingsample.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKey(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name ="prev_key")
    val prevKey: Int?,
    @ColumnInfo(name ="next_key")
    val nextKey: Int?,
)