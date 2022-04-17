package com.example.pagingsample.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.example.pagingsample.datasource.local.dao.EpisodeCharacterCrossRefDao
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.character.CharacterDao
import com.example.pagingsample.datasource.local.dao.character.CharacterDetailsDao
import com.example.pagingsample.datasource.local.dao.episode.EpisodeDao
import com.example.pagingsample.datasource.local.dao.episode.EpisodeDetailsDao
import com.example.pagingsample.datasource.local.dao.location.LocationDao
import com.example.pagingsample.datasource.local.dao.location.LocationDetailsDao
import com.example.pagingsample.model.RemoteKey
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.character.CharacterDetails
import com.example.pagingsample.model.crossref.EpisodeCharacterCrossRef
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.episode.EpisodeDetails
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.model.location.LocationDetails
import com.example.pagingsample.utils.SuspendVoidCallback

@Database(
    entities = [
        RemoteKey::class,
        Character::class,
        CharacterDetails::class,
        Location::class,
        LocationDetails::class,
        Episode::class,
        EpisodeDetails::class,
        EpisodeCharacterCrossRef::class,
    ],
    version = 9,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase(), ITransactionManager {

    abstract fun remoteKeyDao(): RemoteKeyDao

    abstract fun characterDao(): CharacterDao
    abstract fun characterDetailsDao(): CharacterDetailsDao

    abstract fun locationDao(): LocationDao
    abstract fun locationDetailsDao(): LocationDetailsDao

    abstract fun episodeDao(): EpisodeDao
    abstract fun episodeDetailsDao(): EpisodeDetailsDao

    abstract fun episodeCharacterCrossRefDao(): EpisodeCharacterCrossRefDao

    override suspend fun runInTransaction(action: SuspendVoidCallback) {
        withTransaction {
            action.invoke()
        }
    }

}