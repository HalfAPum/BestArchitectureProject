package com.example.pagingsample.di

import androidx.room.Room
import com.example.pagingsample.datasource.local.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()
    }
}
//TODO MOVE TO STRINGS and get from contextx
private const val DATABASE_NAME = "MyDatabase"