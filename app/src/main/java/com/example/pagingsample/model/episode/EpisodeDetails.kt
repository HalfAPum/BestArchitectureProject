package com.example.pagingsample.model.episode

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Episode::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class EpisodeDetails(
    @PrimaryKey
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("created")
    val created: String?,
    @ColumnInfo("episode_code")
    val episodeCode: String?,
)