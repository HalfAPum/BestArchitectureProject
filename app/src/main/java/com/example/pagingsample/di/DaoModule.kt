package com.example.pagingsample.di

import com.example.pagingsample.datasource.local.AppDatabase
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.datasource.local.dao.character.CharacterDao
import com.example.pagingsample.datasource.local.dao.episode.EpisodeDao
import com.example.pagingsample.datasource.local.dao.location.LocationDao
import com.example.pagingsample.model.RemoteKey
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.location.Location
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DaoModule {

    companion object {
        @Provides
        fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao = appDatabase.characterDao()

        @Provides
        fun provideLocationDao(appDatabase: AppDatabase): LocationDao = appDatabase.locationDao()

        @Provides
        fun provideEpisodeDao(appDatabase: AppDatabase): EpisodeDao = appDatabase.episodeDao()

        @Provides
        fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeyDao = appDatabase.remoteKeyDao()
    }


    //Additional variations

    @Binds
    abstract fun baseRemoteKeyDao(dao: RemoteKeyDao): BaseDao<RemoteKey>

    @Binds
    abstract fun baseDaoCharacter(dao: CharacterDao): BaseDao<Character>

    @Binds
    abstract fun getPagingSourceDaoCharacter(dao: CharacterDao): BaseGetPagingSourceDao<Character>

    @Binds
    abstract fun baseDaoLocation(dao: LocationDao): BaseDao<Location>

    @Binds
    abstract fun getPagingSourceDaoLocation(dao: LocationDao): BaseGetPagingSourceDao<Location>

    @Binds
    abstract fun baseDaoEpisode(dao: EpisodeDao): BaseDao<Episode>

    @Binds
    abstract fun getPagingSourceDaoEpisode(dao: EpisodeDao): BaseGetPagingSourceDao<Episode>

}