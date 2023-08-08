package com.example.shacklehotelbuddy.feature_hotel_search.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.SearchQuery
import com.example.shacklehotelbuddy.feature_hotel_search.presentation.ui.detail.DetailView
import com.example.shacklehotelbuddy.feature_hotel_search.presentation.utils.Screen
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShackleHotelBuddyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.SearchQueryScreen.route
                ) {
                    composable(route = Screen.SearchQueryScreen.route) {
                        val viewModel: MainViewModel = hiltViewModel()
                        val searchQueryList = viewModel.searchQueryList.collectAsState(emptyList())

                        SearchQueryScreen(navController,searchQueryList.value, onSearchClick = {
                            viewModel.insertSearchQuery(it)
                            navController.currentBackStackEntry?.savedStateHandle?.apply{
                                set("searchQuery", it)
                            }
                            navController.navigate(Screen.PropertiesListScreen.route)
                        })
                    }

                    composable(
                        route = Screen.PropertiesListScreen.route
                    ) {
                        val searchQuery = navController.previousBackStackEntry?.savedStateHandle?.get<SearchQuery>("searchQuery")
                        val viewModel: MainViewModel = hiltViewModel()

                        viewModel.fetchPropertiesList(searchQuery?.checkedInDate ?: "",
                            searchQuery?.checkedOutDate ?: "",searchQuery?.adults ?: 0,searchQuery?.children ?: 0)

                        val propertiesList = viewModel.propertiesList.collectAsState()
                        val isLoading = viewModel.isLoading.collectAsState()
                        DetailView(navController = navController,propertiesList,isLoading)

                    }
                }
            }
        }
    }
}
