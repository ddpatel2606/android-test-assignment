package com.example.shacklehotelbuddy.feature_hotel_search.presentation.ui.main

import android.annotation.SuppressLint
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.shacklehotelbuddy.HiltTestActivity
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.ADULT_TAG
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.CHECK_IN_DATE_TAG
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.CHECK_OUT_DATE_TAG
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.CHILDREN_TAG
import com.example.shacklehotelbuddy.feature_hotel_search.domain.repository.ShackleRepository
import com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases.GetPropertiesListUseCase
import com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases.GetSearchQueryUseCase
import com.example.shacklehotelbuddy.feature_hotel_search.domain.usecases.InsertSearchQueryUseCase
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {

    // inject all the rules for DI
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    // inject all the rules for compose framework
    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()
    private val activity get() = composeTestRule.activity

    @Inject
    lateinit var shackleRepository: ShackleRepository

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: TestNavHostController

    @Mock
    private lateinit var getSearchQueryUseCase: GetSearchQueryUseCase

    @Mock
    private lateinit var insertSearchQueryUseCase: InsertSearchQueryUseCase

    @Mock
    private lateinit var getPropertiesListUseCase: GetPropertiesListUseCase

    private fun injectAndSetupDi() {
        hiltRule.inject()

        getSearchQueryUseCase = GetSearchQueryUseCase(shackleRepository)
        insertSearchQueryUseCase = InsertSearchQueryUseCase(shackleRepository)
        getPropertiesListUseCase = GetPropertiesListUseCase(shackleRepository)
        viewModel = MainViewModel(getSearchQueryUseCase, insertSearchQueryUseCase, getPropertiesListUseCase)
        setContent()
    }


    @SuppressLint("StateFlowValueCalledInComposition")
    private fun setContent() {
        composeTestRule.setContent {
            ShackleHotelBuddyTheme {
                Surface {
                    navController = TestNavHostController(LocalContext.current)
                    navController.navigatorProvider.addNavigator(ComposeNavigator())
                    SearchQueryScreen(
                        navController,
                        viewModel.searchQueryList.value,
                        onSearchClick = {

                        }
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testViewsDisplayedProperly() = runTest {
        injectAndSetupDi()

        composeTestRule
            .onNodeWithText(activity.getString(R.string.check_in_date))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(activity.getString(R.string.check_out_date))
            .assertIsDisplayed()


        composeTestRule
            .onNodeWithText(activity.getString(R.string.adults))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(activity.getString(R.string.children))
            .assertIsDisplayed()

         composeTestRule.onNodeWithTag(CHECK_IN_DATE_TAG).performTextReplacement("08/09/2023")

        val checkInValue = composeTestRule.onNodeWithTag(CHECK_IN_DATE_TAG)
        Truth.assertThat(checkInValue).isNotNull()

        composeTestRule.onNodeWithTag(CHECK_IN_DATE_TAG).performClick()

        Espresso.pressBack()

        val resultText = "2"

        composeTestRule.onNodeWithTag(ADULT_TAG).performTextInput(resultText)

        composeTestRule.onNodeWithTag(ADULT_TAG).assert(hasText(resultText))

        composeTestRule.onNodeWithTag(CHILDREN_TAG).performTextInput(resultText)

        composeTestRule.onNodeWithTag(CHILDREN_TAG).assert(hasText(resultText))


        composeTestRule.onNodeWithTag(CHECK_OUT_DATE_TAG).performClick()

        Espresso.pressBack()

        composeTestRule.onNodeWithTag(CHECK_OUT_DATE_TAG).performTextReplacement("15/09/2023")

        val checkOutValue = composeTestRule.onNodeWithTag(CHECK_OUT_DATE_TAG)
        Truth.assertThat(checkOutValue).isNotNull()

        Espresso.pressBack()

        composeTestRule.onNodeWithText(activity.getString(R.string.search)).performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        Truth.assertThat(route).isNull()
    }

}
