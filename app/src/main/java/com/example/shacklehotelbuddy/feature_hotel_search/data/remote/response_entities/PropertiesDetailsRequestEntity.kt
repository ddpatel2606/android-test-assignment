package com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities

import com.google.gson.annotations.SerializedName

data class PropertiesDetailsRequestEntity(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("eapid")
    val eaPid: Int,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("propertyId")
    val propertyId: String,
    @SerializedName("siteId")
    val siteId: Int
)