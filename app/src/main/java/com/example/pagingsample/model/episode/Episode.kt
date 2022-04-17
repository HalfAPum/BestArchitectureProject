package com.example.pagingsample.model.episode

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingsample.model.interfaces.Identifiable
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Episode(
    @PrimaryKey
    @ColumnInfo("id")
    override val id: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("air_date")
    val airDate: String?,
) : Identifiable, Parcelable