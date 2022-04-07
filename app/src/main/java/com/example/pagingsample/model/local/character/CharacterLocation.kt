package com.example.pagingsample.model.local.character

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterLocation(
    @ColumnInfo("location_name")
    val name: String,
    @ColumnInfo("location_url")
    val url: String,
) : Parcelable