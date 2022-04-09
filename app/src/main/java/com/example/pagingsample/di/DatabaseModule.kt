package com.example.pagingsample.di

import com.example.pagingsample.datasource.local.AppDatabase
import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.datasource.local.dao.CharacterDao
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.local.character.Character
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
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao = appDatabase.characterDao()

    @Singleton
    @Provides
    fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeyDao = appDatabase.remoteKeyDao()

    @Singleton
    @Provides
    fun provideBaseDaoCharacter(characterDao: CharacterDao): BaseDao<Character> = characterDao

    @Singleton
    @Provides
    fun provideBaseGetPagingSourceDaoCharacter(characterDao: CharacterDao): BaseGetPagingSourceDao<Character> = characterDao

}