package com.example.shacklehotelbuddy.feature_hotel_search.presentation.utils

sealed class Screen(val route: String) {
    object SearchQueryScreen: Screen("search_query_screen")
    object PropertiesListScreen: Screen("properties_list_screen")
}
