package com.example.shacklehotelbuddy.feature_hotel_search.presentation.ui.main

import com.example.shacklehotelbuddy.MainCoroutineRule
import com.example.shacklehotelbuddy.di.RepositoryModule
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.SearchQuery
import com.example.shacklehotelbuddy.feature_hotel_search.domain.repository.ShackleRepository
import com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases.GetPropertiesListUseCase
import com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases.GetSearchQueryUseCase
import com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases.InsertSearchQueryUseCase
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@UninstallModules(value = [RepositoryModule::class])
class MainViewModelTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Inject
    lateinit var shackleRepository: ShackleRepository

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var getSearchQueryUseCase: GetSearchQueryUseCase

    @Mock
    private lateinit var insertSearchQueryUseCase: InsertSearchQueryUseCase

    @Mock
    private lateinit var getPropertiesListUseCase: GetPropertiesListUseCase

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        getSearchQueryUseCase = GetSearchQueryUseCase(shackleRepository)
        insertSearchQueryUseCase = InsertSearchQueryUseCase(shackleRepository)
        getPropertiesListUseCase = GetPropertiesListUseCase(shackleRepository)
        viewModel = MainViewModel(getSearchQueryUseCase, insertSearchQueryUseCase, getPropertiesListUseCase)
    }

    @Test
    fun testPropertiesList() = runTest {
        Truth.assertThat(viewModel.isLoading.value).isFalse()
        Truth.assertThat(viewModel.propertiesList.value).isEmpty()
        viewModel.fetchPropertiesList("2/3/2023","2/4/2023",3,4)
        advanceUntilIdle()
        Truth.assertThat(viewModel.propertiesList.value).isNotEmpty()
    }

    @Test
    fun testSearchQueryList() = runTest {
        Truth.assertThat(viewModel.isLoading.value).isFalse()
        Truth.assertThat(viewModel.searchQueryList.value).isEmpty()
        viewModel.insertSearchQuery(SearchQuery(3,"2/3/2023","2/4/2023",3,4,9L))
        advanceUntilIdle()
        Truth.assertThat(viewModel.searchQueryList.value).isNotEmpty()
    }

}