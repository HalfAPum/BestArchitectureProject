package com.example.pagingsample.model.episode

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.crossref.EpisodeCharacterCrossRef

data class EpisodeDetailsWithCharacters(
    @Embedded
    val details: EpisodeDetails,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            EpisodeCharacterCrossRef::class,
            parentColumn = "episode_id",
            entityColumn = "character_id",
        ),
    )
    val characters: List<Character>
)