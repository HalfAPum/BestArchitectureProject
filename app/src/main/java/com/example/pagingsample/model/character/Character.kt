package com.example.pagingsample.model.character

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingsample.model.interfaces.Identifiable
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Character(
    @PrimaryKey
    @ColumnInfo("id")
    override val id: String,
    @ColumnInfo("name")
    val name: String? = null,
    @ColumnInfo("image")
    val image: String? = null,
    @ColumnInfo("status")
    val status: String? = null,
    @ColumnInfo("gender")
    val gender: String? = null,
) : Identifiable, Parcelable