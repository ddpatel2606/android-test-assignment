package com.example.shacklehotelbuddy.feature_hotel_search.domain.utils

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}