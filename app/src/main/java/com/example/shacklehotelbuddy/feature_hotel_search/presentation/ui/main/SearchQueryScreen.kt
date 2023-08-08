package com.example.shacklehotelbuddy.feature_hotel_search.presentation.ui.main

import android.app.DatePickerDialog
import android.view.KeyEvent
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.ADULT_TAG
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.CHECK_IN_DATE_TAG
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.CHECK_OUT_DATE_TAG
import com.example.shacklehotelbuddy.feature_hotel_search.data.constant.CHILDREN_TAG
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.SearchQuery
import com.example.shacklehotelbuddy.feature_hotel_search.presentation.utils.Screen
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import com.example.shacklehotelbuddy.ui.theme.Teal
import com.example.shacklehotelbuddy.ui.theme.White
import java.util.Calendar


/**
 * SearchQueryScreen
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchQueryScreen(
    navController: NavController,
    searchQueryList: List<SearchQuery>,
    onSearchClick: (SearchQuery) -> Unit
) {

    val context = LocalContext.current

    var checkInSelectedDateText by remember { mutableStateOf("") }
    var adultText by remember { mutableStateOf("") }
    var childrenText by remember { mutableStateOf("") }
    var checkOutSelectedDateText by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val checkInYear = calendar[Calendar.YEAR]
    val checkInMonth = calendar[Calendar.MONTH]
    val checkInDayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    val checkOutYear = calendar[Calendar.YEAR]
    val checkOutMonth = calendar[Calendar.MONTH]
    val checkOutDayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    val focusManager = LocalFocusManager.current

    val checkedInDatePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            checkInSelectedDateText = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, checkInYear, checkInMonth, checkInDayOfMonth
    )
    checkedInDatePicker.datePicker.minDate = calendar.timeInMillis

    val checkedOutDatePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            checkOutSelectedDateText = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, checkOutYear, checkOutMonth, checkOutDayOfMonth
    )
    checkedOutDatePicker.datePicker.minDate = calendar.timeInMillis

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillWidth
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.85f, true),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(16.dp),
                    text = stringResource(R.string.select_guests_date_and_time),
                    style = ShackleHotelBuddyTheme.typography.bodyHigher,
                    color = ShackleHotelBuddyTheme.colors.white
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(
                            width = 10.dp, color = ShackleHotelBuddyTheme.colors.white,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .clip(shape = RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp))
                        .background(ShackleHotelBuddyTheme.colors.white)
                        .padding(16.dp)
                ) {

                    Column {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(60.dp)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.event_upcoming),
                                    contentDescription = stringResource(R.string.content_desc_checkedinimage),
                                    modifier = Modifier
                                        .height(20.dp)
                                        .width(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    modifier = Modifier
                                        .background(Color.Transparent),
                                    text = stringResource(R.string.check_in_date),
                                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                    color = ShackleHotelBuddyTheme.colors.grayText
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
                            )

                            OutlinedTextField(
                                value = checkInSelectedDateText,
                                enabled = false,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f).testTag(CHECK_IN_DATE_TAG)
                                    .clickable { checkedInDatePicker.show() },
                                onValueChange = { checkInSelectedDateText = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.date_hint),
                                        style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                        color = ShackleHotelBuddyTheme.colors.grayText
                                    )
                                },
                                textStyle = ShackleHotelBuddyTheme.typography.bodyMedium,
                                maxLines = 1,
                                colors = textFieldColors()
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(ShackleHotelBuddyTheme.colors.grayBorder)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(60.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.event_available),
                                    contentDescription = stringResource(R.string.content_desc_checkedoutimage),
                                    modifier = Modifier
                                        .height(20.dp)
                                        .width(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    modifier = Modifier
                                        .background(Color.Transparent),
                                    text = stringResource(R.string.check_out_date),
                                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                    color = ShackleHotelBuddyTheme.colors.grayText
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
                            )

                            OutlinedTextField(
                                value = checkOutSelectedDateText,
                                enabled = false,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f).testTag(CHECK_OUT_DATE_TAG)
                                    .clickable { checkedOutDatePicker.show() },
                                onValueChange = { checkOutSelectedDateText = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.date_hint),
                                        style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                        color = ShackleHotelBuddyTheme.colors.grayText
                                    )
                                },
                                textStyle = ShackleHotelBuddyTheme.typography.bodyMedium,
                                maxLines = 1,
                                colors = textFieldColors()
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(ShackleHotelBuddyTheme.colors.grayBorder)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(60.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.person),
                                    contentDescription = stringResource(R.string.content_desc_adultsimage),
                                    modifier = Modifier
                                        .height(20.dp)
                                        .width(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    modifier = Modifier
                                        .background(Color.Transparent),
                                    text = stringResource(R.string.adults),
                                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                    color = ShackleHotelBuddyTheme.colors.grayText
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
                            )

                            OutlinedTextField(
                                value = adultText,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f).testTag(ADULT_TAG)
                                    .onPreviewKeyEvent {
                                        if (it.key == Key.Tab && it.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
                                            focusManager.moveFocus(FocusDirection.Down)
                                            true
                                        } else {
                                            false
                                        }
                                    },
                                onValueChange = { adultText = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.adult_hint),
                                        style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                        color = ShackleHotelBuddyTheme.colors.grayText
                                    )
                                },
                                textStyle = ShackleHotelBuddyTheme.typography.bodyMedium,
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                colors = textFieldColors(),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                                )
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(ShackleHotelBuddyTheme.colors.grayBorder)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(60.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.supervisor_account),
                                    contentDescription = stringResource(R.string.content_desc_childrenimage),
                                    modifier = Modifier
                                        .height(20.dp)
                                        .width(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    modifier = Modifier
                                        .background(Color.Transparent),
                                    text = stringResource(R.string.children),
                                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                    color = ShackleHotelBuddyTheme.colors.grayText
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
                            )

                            OutlinedTextField(
                                value = childrenText,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f).testTag(CHILDREN_TAG)
                                    .onPreviewKeyEvent {
                                        if (it.key == Key.Tab && it.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
                                            focusManager.moveFocus(FocusDirection.Down)
                                            true
                                        } else {
                                            false
                                        }
                                    },
                                onValueChange = { childrenText = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.adult_hint),
                                        style = ShackleHotelBuddyTheme.typography.bodyMedium,
                                        color = ShackleHotelBuddyTheme.colors.grayText
                                    )
                                },
                                textStyle = ShackleHotelBuddyTheme.typography.bodyMedium,
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                ),
                                colors = textFieldColors(),
                                keyboardActions = KeyboardActions(
                                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                                )
                            )
                        }

                    }

                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .absolutePadding(16.dp, 0.dp, 0.dp, 8.dp),
                    text = stringResource(R.string.recent_searches),
                    style = ShackleHotelBuddyTheme.typography.bodySemiBoldMedium,
                    color = ShackleHotelBuddyTheme.colors.white
                )

                LazyColumn {
                    items(
                        items = searchQueryList){
                        SearchQueryItem(
                            task = it,
                            navController
                        )
                    }
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f, true),
                verticalAlignment = Alignment.Bottom
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .border(
                            width = 0.dp, color = Teal,
                            shape = RoundedCornerShape(25.dp)
                        ),
                    onClick = {
                        focusManager.clearFocus()
                        if(checkInSelectedDateText.isNotEmpty() &&
                            checkOutSelectedDateText.isNotEmpty() &&
                            adultText.isNotEmpty() && childrenText.isNotEmpty()) {

                            val searchQuery = SearchQuery(
                                checkedInDate = checkInSelectedDateText,
                                checkedOutDate = checkOutSelectedDateText,
                                adults = adultText.toInt(),
                                children = childrenText.toInt(),
                                time = System.currentTimeMillis()
                            )

                            onSearchClick(searchQuery)
                        }
                        else
                        {
                            Toast.makeText(context, context.getString(R.string.please_enter_valid_information),
                                Toast.LENGTH_SHORT).show()
                        }
                    },
                    shape = RoundedCornerShape(25), colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Teal,
                        disabledContentColor = White,
                        containerColor = Teal,
                        disabledContainerColor = White
                    )
                ) {
                    Text(
                        text = stringResource(R.string.search),
                        style = ShackleHotelBuddyTheme.typography.bodyBoldMedium,
                        color = ShackleHotelBuddyTheme.colors.white,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}


/**
 * All TextField Colors
 */
@Composable
fun textFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = ShackleHotelBuddyTheme.colors.grayText,
    disabledTextColor = ShackleHotelBuddyTheme.colors.grayText,
    cursorColor = ShackleHotelBuddyTheme.colors.grayText,
    focusedBorderColor = Color.Transparent,
    unfocusedBorderColor = Color.Transparent,
    disabledBorderColor = Color.Transparent,
)


/**
 * Search Query Item
 */
@Composable
private fun SearchQueryItem(
    task: SearchQuery,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .absolutePadding(12.dp, 8.dp, 12.dp, 8.dp)
            .border(
                width = 6.dp, color = ShackleHotelBuddyTheme.colors.white,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
            .background(ShackleHotelBuddyTheme.colors.white)
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .clickable {

                    navController.currentBackStackEntry?.savedStateHandle?.apply{
                        set("searchQuery", task)
                    }
                    navController.navigate(Screen.PropertiesListScreen.route)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.manage_history),
                contentDescription = stringResource(R.string.searchinimage),
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(ShackleHotelBuddyTheme.colors.grayBorder)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                text = "${task.checkedInDate} - ${task.checkedOutDate}    ${task.adults} adult, ${task.children} children",
                style = ShackleHotelBuddyTheme.typography.bodyMedium,
                color = ShackleHotelBuddyTheme.colors.grayText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}