package com.example.pagingsample.di

import com.example.pagingsample.datasource.local.AppDatabase
import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.datasource.local.dao.character.CharacterDao
import com.example.pagingsample.datasource.local.dao.episode.EpisodeDao
import com.example.pagingsample.datasource.local.dao.location.LocationDao
import com.example.pagingsample.model.RemoteKey
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.location.Location
import org.koin.dsl.module

val databaseModule = module {

    fun provideITransactionManager(
        appDatabase: AppDatabase
    ): ITransactionManager = appDatabase
    factory { provideITransactionManager(get()) }


    fun provideCharacterDao(
        appDatabase: AppDatabase
    ) = appDatabase.characterDao()
    factory { provideCharacterDao(get()) }

    fun provideCharacterBaseDao(
        dao: CharacterDao
    ) : BaseDao<Character> = dao
    genericFactory { provideCharacterBaseDao(get()) }

    fun provideCharacterBasePagingSourceDao(
        dao: CharacterDao
    ) : BaseGetPagingSourceDao<Character> = dao
    genericFactory { provideCharacterBasePagingSourceDao(get()) }

    fun provideLocationDao(
        appDatabase: AppDatabase
    ) = appDatabase.locationDao()
    factory { provideLocationDao(get()) }

    fun provideLocationBaseDao(
        dao: LocationDao
    ) : BaseDao<Location> = dao
    genericFactory { provideLocationBaseDao(get()) }

    fun provideLocationBasePagingSourceDao(
        dao: LocationDao
    ) : BaseGetPagingSourceDao<Location> = dao
    genericFactory { provideLocationBasePagingSourceDao(get()) }

    fun provideEpisodeDao(
        appDatabase: AppDatabase
    ) = appDatabase.episodeDao()
    factory { provideEpisodeDao(get()) }

    fun provideEpisodeBaseDao(
        dao: EpisodeDao
    ) : BaseDao<Episode> = dao
    genericFactory { provideEpisodeBaseDao(get()) }

    fun provideEpisodeBasePagingSourceDao(
        dao: EpisodeDao
    ) : BaseGetPagingSourceDao<Episode> = dao
    genericFactory { provideEpisodeBasePagingSourceDao(get()) }

    fun provideRemoteKeyDao(
        appDatabase: AppDatabase
    ) = appDatabase.remoteKeyDao()
    factory { provideRemoteKeyDao(get()) }

    fun provideRemoteKeyBaseDao(
        appDatabase: AppDatabase
    ): BaseDao<RemoteKey> = appDatabase.remoteKeyDao()
    genericFactory { provideRemoteKeyBaseDao(get()) }

}