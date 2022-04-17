package com.example.pagingsample.model.episode

import androidx.room.Embedded
import androidx.room.Relation

data class EpisodeWithDetails(
    @Embedded
    val episode: Episode,
    @Relation(
        entity = EpisodeDetails::class,
        parentColumn = "id",
        entityColumn = "id"
    )
    val detailsWithCharacters: EpisodeDetailsWithCharacters,
)