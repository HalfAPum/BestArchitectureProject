package com.example.pagingsample.di

import android.content.Context
import androidx.room.Room
import com.example.pagingsample.datasource.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppDatabaseModule::class]
)
object TestAppDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomInMemoryDb(
        @ApplicationContext applicationContext: Context
    ) = Room.inMemoryDatabaseBuilder(
        applicationContext,
        AppDatabase::class.java,
    ).build()

}