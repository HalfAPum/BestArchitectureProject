package com.example.pagingsample.model.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pagingsample.model.interfaces.Identifiable

@Entity
data class Character(
    @PrimaryKey
    @ColumnInfo("id")
    override val id: Long,
    @ColumnInfo("name")
    val name: String? = null,
    @ColumnInfo("image")
    val image: String? = null,
    @ColumnInfo("status")
    val status: String? = null,
    @ColumnInfo("gender")
    val gender: String? = null,
) : Identifiable