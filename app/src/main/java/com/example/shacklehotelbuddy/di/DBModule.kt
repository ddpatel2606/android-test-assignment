package com.example.shacklehotelbuddy.di

import android.content.Context
import androidx.room.Room
import com.example.shacklehotelbuddy.feature_hotel_search.data.local.dao.SearchQueryDao
import com.example.shacklehotelbuddy.feature_hotel_search.data.local.database.AppDatabase
import com.example.shacklehotelbuddy.feature_hotel_search.data.local.database.AppDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): SearchQueryDao = appDatabase.searchQueryDao()

}