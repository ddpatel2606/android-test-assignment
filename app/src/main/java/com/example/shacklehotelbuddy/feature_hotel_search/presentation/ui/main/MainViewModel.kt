package com.example.shacklehotelbuddy.feature_hotel_search.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.Property
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.PropertyList
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.SearchQuery
import com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases.GetPropertiesListUseCase
import com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases.GetSearchQueryUseCase
import com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases.InsertSearchQueryUseCase
import com.example.shacklehotelbuddy.feature_hotel_search.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getSearchQueryUseCase: GetSearchQueryUseCase,
                                        private val insertSearchQueryUseCase: InsertSearchQueryUseCase,
                                        private val getPropertiesListUseCase: GetPropertiesListUseCase) : ViewModel() {

    private val _searchQueryList = MutableStateFlow(emptyList<SearchQuery>())
    val searchQueryList get() = _searchQueryList.asStateFlow()

    private val _propertiesList = MutableStateFlow(emptyList<Property>())
    val propertiesList get() = _propertiesList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading.asStateFlow()

    init {
        fetchSearchQueryList()
    }

    private fun fetchSearchQueryList() {
        viewModelScope.launch {
            getSearchQueryUseCase().collect { response ->
                _searchQueryList.value = response
            }
        }
    }

    fun insertSearchQuery(searchQuery: SearchQuery){
        viewModelScope.launch {
            insertSearchQueryUseCase(searchQuery).collect{
                fetchSearchQueryList()
            }
        }
    }

    fun fetchPropertiesList(
        checkInDate: String,
        checkOutDate: String,
        adult: Int,
        children: Int
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            getPropertiesListUseCase(
                checkedInDate = checkInDate,
                checkedOutDate = checkOutDate,
                adults = adult,
                children = children
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _propertiesList.value = (response.data as PropertyList).results
                        _isLoading.value = false
                    }

                    is Resource.Error -> {
                        _isLoading.value = false
                    }
                }
            }
        }
    }

}