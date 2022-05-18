package com.example.pagingsample.di

import com.example.pagingsample.model.RemoteKey
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.location.Location
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object EntityModule {

    @Provides
    fun provideCharacterClass(): Class<Character> = Character::class.java

    @Provides
    fun provideEpisodeClass(): Class<Episode> = Episode::class.java

    @Provides
    fun provideLocationClass(): Class<Location> = Location::class.java

    @Provides
    fun provideRemoteKeyClass(): Class<RemoteKey> = RemoteKey::class.java

}