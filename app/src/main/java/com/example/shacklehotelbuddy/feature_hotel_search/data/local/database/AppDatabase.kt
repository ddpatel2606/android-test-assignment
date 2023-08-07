package com.example.shacklehotelbuddy.feature_hotel_search.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shacklehotelbuddy.feature_hotel_search.data.local.dao.SearchQueryDao
import com.example.shacklehotelbuddy.feature_hotel_search.data.local.entities.SearchQueryEntity

@Database(entities = [SearchQueryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchQueryDao(): SearchQueryDao

    companion object {
        const val DATABASE_NAME = "shackle_db"
    }
}