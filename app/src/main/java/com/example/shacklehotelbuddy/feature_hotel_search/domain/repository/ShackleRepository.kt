package com.example.shacklehotelbuddy.feature_hotel_search.domain.repository

import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.PropertyList
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.SearchQuery
import com.example.shacklehotelbuddy.feature_hotel_search.domain.utils.Resource

interface ShackleRepository {
    suspend fun getPropertiesList(checkedInDate: String, checkedOutDate: String, adult: Int, children: Int): Resource<PropertyList>
    suspend fun getSearchQueryList(): List<SearchQuery>
    suspend fun insertSearchQuery(sQuery: SearchQuery)
    suspend fun deleteSearchQuery(sQuery: SearchQuery)
}