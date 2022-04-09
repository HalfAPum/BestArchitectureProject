package com.example.pagingsample.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.example.pagingsample.datasource.local.dao.CharacterDao
import com.example.pagingsample.datasource.local.dao.EpisodeDao
import com.example.pagingsample.datasource.local.dao.LocationDao
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.model.Character
import com.example.pagingsample.model.Episode
import com.example.pagingsample.model.Location
import com.example.pagingsample.model.RemoteKey
import com.example.pagingsample.utils.SuspendVoidCallback

@Database(
    entities = [
        RemoteKey::class,
        Character::class,
        Location::class,
        Episode::class,
    ],
    version = 6,
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