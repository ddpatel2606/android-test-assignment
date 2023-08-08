package com.example.shacklehotelbuddy.feature_hotel_search.data.constant

const val HTTP_REQUEST_TIMEOUT = 30L
const val INTERCEPTOR_FOR_REQUEST = "interceptor_request"
const val INTERCEPTOR_FOR_CACHE = "interceptor_cache"
const val CACHE_SIZE = 1024 * 1024 * 10L // 10 MB
const val CACHE_MAX_STALE_TIME = 60 * 60 * 24 * 2 // 2 days
const val CACHE_MAX_AGE = 30 * 60  // 30 min

const val DEFAULT_CURRENCY = "USD"
const val DEFAULT_LOCALE = "en_US"
const val DEFAULT_RESULT_SIZE = 100
const val DEFAULT_RESULT_STARTING_INDEX = 0
const val DEFAULT_CHILDREN_AGE = 5
const val DEFAULT_REGION_ID = "6054439"
const val DEFAULT_PRICE_SORT = "PRICE_LOW_TO_HIGH"
const val DEFAULT_EAP_ID = 1
const val DEFAULT_SITE_ID = 300000001
const val DEFAULT_MAX_PRICE = 500
const val DEFAULT_MIN_PRICE = 100

const val CHECK_IN_DATE_TAG = "CHECK_IN_DATE_TAG"
const val CHECK_OUT_DATE_TAG = "CHECK_OUT_DATE_TAG"
const val ADULT_TAG = "ADULT_TAG"
const val CHILDREN_TAG = "CHILDREN_TAG"