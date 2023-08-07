package com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities

import com.google.gson.annotations.SerializedName

data class PropertiesListResponseEntity(
    @SerializedName("data")
    val data: PropertiesListResponseData
)

data class PropertiesListResponseData(
    @SerializedName("propertySearch")
    val propertySearch: PropertySearchEntity
)

data class PropertySearchEntity(
    @SerializedName("properties")
    val properties: List<PropertyEntity>
)

data class PropertyEntity(
    @SerializedName("availability")
    val availability: AvailabilityEntity,
    @SerializedName("destinationInfo")
    val destinationInfo: DestinationInfoEntity,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: PropertyPriceEntity,
    @SerializedName("propertyImage")
    val propertyImageEntity: PropertyImageEntity,
    @SerializedName("neighborhood")
    val neighborhood: NeighBorHoodEntity,
    @SerializedName("star")
    val star: String
)

data class NeighBorHoodEntity(
    @SerializedName("name")
    val name: String
)

data class AvailabilityEntity(
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("minRoomsLeft")
    val minRoomsLeft: Int
)

data class DestinationInfoEntity(
    @SerializedName("distanceFromDestination")
    val distanceFromDestination: DistanceFromDestinationEntity
)

data class PropertyPriceEntity(
    @SerializedName("lead")
    val lead: LeadEntity,
    @SerializedName("priceMessages")
    val priceMessages: List<PriceMessageEntity>
)

data class PropertyImageEntity(
    @SerializedName("image")
    val image: ImageEntity
)

data class DistanceFromDestinationEntity(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double
)

data class LeadEntity(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("formatted")
    val formatted: String
)

data class PriceMessageEntity(
    @SerializedName("value")
    val value: String
)

data class ImageEntity(
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String
)