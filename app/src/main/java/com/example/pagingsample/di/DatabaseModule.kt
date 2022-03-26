package com.example.pagingsample.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pagingsample.datasource.local.AppDatabase
import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.datasource.local.dao.CharacterDao
import com.example.pagingsample.datasource.local.dao.PassengerDao
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.model.local.character.Character
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "MyDatabase"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideTransactionManager(appDatabase: AppDatabase): ITransactionManager = appDatabase

    @Singleton
    @Provides
    fun providePagingLoadDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun providePassengerDao(appDatabase: AppDatabase): PassengerDao = appDatabase.passengerDao()

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao = appDatabase.characterDao()

    @Singleton
    @Provides
    fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeyDao = appDatabase.remoteKeyDao()

    @Singleton
    @Provides
    fun provideBaseDaoCharacter(characterDao: CharacterDao): BaseDao<Character> = characterDao

    @Singleton
    @Provides
    fun provideBaseDaoPassenger(passengerDao: PassengerDao): BaseDao<Passenger> = passengerDao
}