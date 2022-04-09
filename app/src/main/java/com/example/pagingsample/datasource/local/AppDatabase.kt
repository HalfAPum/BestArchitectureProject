package com.example.pagingsample.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.example.pagingsample.datasource.local.dao.CharacterDao
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.model.local.RemoteKey
import com.example.pagingsample.model.local.character.Character
import com.example.pagingsample.utils.SuspendVoidCallback

@Database(
    entities = [
        RemoteKey::class,
        Character::class,
    ],
    version = 5,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase(), ITransactionManager {

    abstract fun remoteKeyDao(): RemoteKeyDao

    abstract fun characterDao(): CharacterDao

    override suspend fun runInTransaction(action: SuspendVoidCallback) {
        withTransaction {
            action.invoke()
        }
    }

}