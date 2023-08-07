package com.example.shacklehotelbuddy.feature_hotel_search.data.local.dao

import androidx.room.*
import com.example.shacklehotelbuddy.feature_hotel_search.data.local.entities.SearchQueryEntity

@Dao
interface SearchQueryDao {

    @Query("SELECT * FROM searchqueryentity ORDER BY date_added DESC")
    suspend fun getSearchQuery(): List<SearchQueryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchQuery(entity: SearchQueryEntity)

    @Delete
    suspend fun deleteSearchQuery(entity: SearchQueryEntity)
}