package com.example.shacklehotelbuddy.feature_hotel_search.data.repository_impl


import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_CHILDREN_AGE
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_CURRENCY
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_EAP_ID
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_LOCALE
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_MAX_PRICE
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_MIN_PRICE
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_PRICE_SORT
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_REGION_ID
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_RESULT_SIZE
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_RESULT_STARTING_INDEX
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.DEFAULT_SITE_ID
import com.example.shacklehotelbuddy.feature_hotel_search.data.local.dao.SearchQueryDao
import com.example.shacklehotelbuddy.feature_hotel_search.data.mapper.toPropertyList
import com.example.shacklehotelbuddy.feature_hotel_search.data.mapper.toSearchQuery
import com.example.shacklehotelbuddy.feature_hotel_search.data.mapper.toSearchQueryEntity
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.api_interface.ShackleApiInterface
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.CheckInOutDate
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.Children
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.Destination
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.Filters
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.Price
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.PropertiesListRequestEntity
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.Room
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.PropertyList
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.SearchQuery
import com.example.shacklehotelbuddy.feature_hotel_search.domain.repository.ShackleRepository
import com.example.shacklehotelbuddy.feature_hotel_search.domain.utils.Resource
import com.example.shacklehotelbuddy.feature_hotel_search.domain.utils.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ShackleRepositoryImpl @Inject constructor(
    private val api: ShackleApiInterface,
    private val safeApiCall: SafeApiCall,
    private val sQDao: SearchQueryDao
) : ShackleRepository {

    override suspend fun getPropertiesList(
        checkedInDate: String,
        checkedOutDate: String,
        adult: Int,
        children: Int
    ): Resource<PropertyList> = safeApiCall.execute {
        val checkedInDateArray = checkedInDate.split("/")
        val checkedOutDateArray = checkedOutDate.split("/")
        val propertyEntity = PropertiesListRequestEntity(CheckInOutDate(checkedInDateArray[0].toInt(),checkedInDateArray[1].toInt(),checkedInDateArray[2].toInt()),
            CheckInOutDate(checkedOutDateArray[0].toInt(),checkedOutDateArray[1].toInt(),checkedOutDateArray[2].toInt()),
            DEFAULT_CURRENCY, Destination(DEFAULT_REGION_ID),DEFAULT_EAP_ID, Filters(Price(
                DEFAULT_MAX_PRICE,DEFAULT_MIN_PRICE)),DEFAULT_LOCALE,DEFAULT_RESULT_SIZE,
            DEFAULT_RESULT_STARTING_INDEX, listOf(Room(adult, listOf(Children(DEFAULT_CHILDREN_AGE)))),DEFAULT_SITE_ID,DEFAULT_PRICE_SORT)

        api.getPropertiesList(propertyEntity).toPropertyList()
    }

    override suspend fun getSearchQueryList(): List<SearchQuery> {
        return sQDao.getSearchQuery().map{ it.toSearchQuery() }
    }

    override suspend fun insertSearchQuery(sQuery: SearchQuery) {
        sQDao.insertSearchQuery(sQuery.toSearchQueryEntity())
    }

    override suspend fun deleteSearchQuery(sQuery: SearchQuery) {
        sQDao.deleteSearchQuery(sQuery.toSearchQueryEntity())
    }
}