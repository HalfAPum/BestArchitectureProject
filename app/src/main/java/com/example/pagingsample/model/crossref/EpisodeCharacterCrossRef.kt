package com.example.pagingsample.model.crossref

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.episode.Episode

@Entity(
    primaryKeys = ["episode_id", "character_id"],
    foreignKeys = [
        ForeignKey(
            entity = Episode::class,
            parentColumns = ["id"],
            childColumns = ["episode_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Character::class,
            parentColumns = ["id"],
            childColumns = ["character_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class EpisodeCharacterCrossRef(
    @ColumnInfo("episode_id")
    val episodeId: String,
    @ColumnInfo("character_id")
    val characterId: String,
)