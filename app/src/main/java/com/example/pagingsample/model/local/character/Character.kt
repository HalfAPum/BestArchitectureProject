package com.example.pagingsample.model.local.character

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingsample.model.local.interfaces.Identifiable
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Character(
    @PrimaryKey
    @ColumnInfo("id")
    override val id: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("status")
    val status: String,
    @ColumnInfo("species")
    val species: String,
    @ColumnInfo("type")
    val type: String,
    @ColumnInfo("gender")
    val gender: String,
    @Embedded
    val location: CharacterLocation,
    @ColumnInfo("image")
    val image: String,
    @ColumnInfo("created")
    val created: String,
) : Identifiable, Parcelable