package com.example.pagingsample.di

import android.content.Context
import androidx.room.Room
import com.example.pagingsample.datasource.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "MyDatabase"
        ).fallbackToDestructiveMigration().build()
    }

}