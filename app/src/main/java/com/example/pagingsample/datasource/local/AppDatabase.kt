package com.example.pagingsample.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.character.CharacterDao
import com.example.pagingsample.datasource.local.dao.episode.EpisodeDao
import com.example.pagingsample.datasource.local.dao.location.LocationDao
import com.example.pagingsample.model.RemoteKey
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.utils.SuspendVoidCallback

@Database(
    entities = [
        RemoteKey::class,
        Character::class,
        Location::class,
        Episode::class,
    ],
    version = 11,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase(), ITransactionManager {

    abstract fun remoteKeyDao(): RemoteKeyDao

    abstract fun characterDao(): CharacterDao

    abstract fun locationDao(): LocationDao

    abstract fun episodeDao(): EpisodeDao

    override suspend fun runInTransaction(action: SuspendVoidCallback) {
        withTransaction {
            action.invoke()
        }
    }

}