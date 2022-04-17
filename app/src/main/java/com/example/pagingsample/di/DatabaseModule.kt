package com.example.pagingsample.di

import com.example.pagingsample.datasource.local.AppDatabase
import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.datasource.local.dao.EpisodeCharacterCrossRefDao
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.datasource.local.dao.character.CharacterDao
import com.example.pagingsample.datasource.local.dao.character.CharacterDetailsDao
import com.example.pagingsample.datasource.local.dao.episode.EpisodeDao
import com.example.pagingsample.datasource.local.dao.episode.EpisodeDetailsDao
import com.example.pagingsample.datasource.local.dao.location.LocationDao
import com.example.pagingsample.datasource.local.dao.location.LocationDetailsDao
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.location.Location
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTransactionManager(appDatabase: AppDatabase): ITransactionManager = appDatabase

    @Singleton
    @Provides
    fun provideEpisodeCharacterCrossRefDao(
        appDatabase: AppDatabase
    ): EpisodeCharacterCrossRefDao = appDatabase.episodeCharacterCrossRefDao()

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao = appDatabase.characterDao()

    @Singleton
    @Provides
    fun provideCharacterDetailsDao(appDatabase: AppDatabase): CharacterDetailsDao = appDatabase.characterDetailsDao()

    @Singleton
    @Provides
    fun provideLocationDao(appDatabase: AppDatabase): LocationDao = appDatabase.locationDao()

    @Singleton
    @Provides
    fun provideLocationDetailsDao(appDatabase: AppDatabase): LocationDetailsDao = appDatabase.locationDetailsDao()

    @Singleton
    @Provides
    fun provideEpisodeDao(appDatabase: AppDatabase): EpisodeDao = appDatabase.episodeDao()

    @Singleton
    @Provides
    fun provideEpisodeDetailsDao(appDatabase: AppDatabase): EpisodeDetailsDao = appDatabase.episodeDetailsDao()

    @Singleton
    @Provides
    fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeyDao = appDatabase.remoteKeyDao()

    @Singleton
    @Provides
    fun provideBaseDaoCharacter(characterDao: CharacterDao): BaseDao<Character> = characterDao

    @Singleton
    @Provides
    fun provideBaseGetPagingSourceDaoCharacter(characterDao: CharacterDao): BaseGetPagingSourceDao<Character> = characterDao

    @Singleton
    @Provides
    fun provideBaseDaoLocation(locationDao: LocationDao): BaseDao<Location> = locationDao

    @Singleton
    @Provides
    fun provideBaseGetPagingSourceDaoLocation(locationDao: LocationDao): BaseGetPagingSourceDao<Location> = locationDao

    @Singleton
    @Provides
    fun provideBaseDaoEpisode(episodeDao: EpisodeDao): BaseDao<Episode> = episodeDao

    @Singleton
    @Provides
    fun provideBaseGetPagingSourceDaoEpisode(episodeDao: EpisodeDao): BaseGetPagingSourceDao<Episode> = episodeDao

}