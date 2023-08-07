package com.example.shacklehotelbuddy.feature_hotel_search.data.remote.api_interface

import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.PropertiesDetailsRequestEntity
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.PropertiesListRequestEntity
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.PropertiesListResponseEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface ShackleApiInterface {

    @POST("properties/v2/list")
    suspend fun getPropertiesList(
        @Body propertiesListSearchEntity: PropertiesListRequestEntity
    ): PropertiesListResponseEntity

    @POST("properties/v2/detail")
    suspend fun getPropertiesDetails(
        @Body propertiesDetailsRequestEntity: PropertiesDetailsRequestEntity
    ): String
}