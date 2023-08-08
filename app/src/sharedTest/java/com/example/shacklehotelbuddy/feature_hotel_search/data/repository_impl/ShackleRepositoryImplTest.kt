package com.example.shacklehotelbuddy.feature_hotel_search.data.repository_impl

import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.Property
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.PropertyList
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.SearchQuery
import com.example.shacklehotelbuddy.feature_hotel_search.domain.repository.ShackleRepository
import com.example.shacklehotelbuddy.feature_hotel_search.domain.utils.Resource
import javax.inject.Inject


class ShackleRepositoryImplTest @Inject constructor() : ShackleRepository {

    private val searchQueryList = mutableListOf<SearchQuery>()

    override suspend fun getPropertiesList(
        checkedInDate: String,
        checkedOutDate: String,
        adult: Int,
        children: Int
    ): Resource<PropertyList> {
        return Resource.Success(PropertyList(listOf(Property("test","testName",
            "testImage",
        "$12 night","London","4.5"))))
    }

    override suspend fun getSearchQueryList(): List<SearchQuery> {
        return listOf(SearchQuery(9,"12/2/2022","15/2/2022",1,2,5L))
    }

    override suspend fun insertSearchQuery(sQuery: SearchQuery) {
        searchQueryList.add(sQuery)
    }

    override suspend fun deleteSearchQuery(sQuery: SearchQuery) {
        searchQueryList.remove(sQuery)
    }


}