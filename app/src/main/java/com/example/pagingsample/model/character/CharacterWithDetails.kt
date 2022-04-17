package com.example.pagingsample.model.character

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithDetails(
    @Embedded
    val character: Character,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val details: CharacterDetails,
)