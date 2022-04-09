package com.example.pagingsample.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingsample.model.interfaces.Identifiable
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Location(
    @PrimaryKey
    @ColumnInfo("id")
    override val id: String,
    @ColumnInfo("name")
    val name: String,
) : Identifiable, Parcelable