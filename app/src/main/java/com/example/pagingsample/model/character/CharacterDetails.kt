package com.example.pagingsample.model.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.pagingsample.model.interfaces.Identifiable

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Character::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class CharacterDetails(
    @PrimaryKey
    @ColumnInfo("id")
    override val id: String,
    @ColumnInfo("created")
    val created: String?,
    @ColumnInfo("last_location_id")
    val lastLocationId: String?,
) : Identifiable