package com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities


import com.google.gson.annotations.SerializedName

data class PropertiesListRequestEntity(
    @SerializedName("checkInDate")
    val checkInDate: CheckInOutDate,
    @SerializedName("checkOutDate")
    val checkOutDate: CheckInOutDate,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("destination")
    val destination: Destination,
    @SerializedName("eapid")
    val eaPid: Int,
    @SerializedName("filters")
    val filters: Filters,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("resultsSize")
    val resultsSize: Int,
    @SerializedName("resultsStartingIndex")
    val resultsStartingIndex: Int,
    @SerializedName("rooms")
    val rooms: List<Room>,
    @SerializedName("siteId")
    val siteId: Int,
    @SerializedName("sort")
    val sort: String
)

data class CheckInOutDate(
    @SerializedName("day")
    val day: Int,
    @SerializedName("month")
    val month: Int,
    @SerializedName("year")
    val year: Int
)

data class Destination(
    @SerializedName("regionId")
    val regionId: String
)

data class Filters(
    @SerializedName("price")
    val price: Price
)

data class Room(
    @SerializedName("adults")
    val adults: Int,
    @SerializedName("children")
    val children: List<Children>
)

data class Price(
    @SerializedName("max")
    val max: Int,
    @SerializedName("min")
    val min: Int
)

data class Children(
    @SerializedName("age")
    val age: Int
)