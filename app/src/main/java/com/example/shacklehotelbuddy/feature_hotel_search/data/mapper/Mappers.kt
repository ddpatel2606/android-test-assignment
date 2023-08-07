package com.example.shacklehotelbuddy.feature_hotel_search.data.mapper

import com.example.shacklehotelbuddy.feature_hotel_search.data.local.entities.SearchQueryEntity
import com.example.shacklehotelbuddy.feature_hotel_search.data.remote.response_entities.PropertiesListResponseEntity
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.Property
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.PropertyList
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.SearchQuery


fun SearchQuery.toSearchQueryEntity() =
    SearchQueryEntity(id, checkedInDate, checkedOutDate, adults, children, time)

fun SearchQueryEntity.toSearchQuery() =
    SearchQuery(id, checkedInDate, checkedOutDate, adults, children, date)


fun PropertiesListResponseEntity.toPropertyList() =
    PropertyList(results = data.propertySearch.properties.map {
        Property(
            it.id,
            it.name,
            it.propertyImageEntity.image.url,
            "${it.price.lead.formatted} ${it.price.priceMessages[0].value}",
            it.neighborhood.name,
            it.star
        )
    })
