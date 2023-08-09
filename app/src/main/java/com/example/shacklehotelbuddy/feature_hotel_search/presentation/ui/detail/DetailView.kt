package com.example.shacklehotelbuddy.feature_hotel_search.presentation.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.Property
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import kotlinx.coroutines.flow.MutableStateFlow


/**
 *  Detail View
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailView(
    navController: NavController,
    propertiesList: State<List<Property>>,
    isLoading: State<Boolean>,
) {
    Scaffold(topBar = { TopAppBarView(navController) }) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            DetailViewBody(propertiesList,isLoading)
        }
    }
}

/**
 *  Detail Body View
 */
@Composable
fun DetailViewBody(propertiesList: State<List<Property>>, isLoading: State<Boolean>) {
    if (!isLoading.value) {
        if (propertiesList.value.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = propertiesList.value
                ) {
                    PropertyItem(
                        property = it
                    )
                }
            }
        } else {
            EmptyDataView()
        }
    } else {
        LoadingView()
    }
}

/**
 *  Empty Data View
 */
@Composable
fun EmptyDataView(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.no_data),
            style = ShackleHotelBuddyTheme.typography.bodyMedium,
            color = ShackleHotelBuddyTheme.colors.grayText
        )
    }
}


/**
 *  Loading View
 */
@Composable
fun LoadingView(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}


/**
 *  PropertyItem
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PropertyItem(
    property: Property
) {
    val imageSize = 200.dp
        Column(
            modifier = Modifier
                .fillMaxWidth().padding(16.dp),
        ) {
            GlideImage(
                model = property.propertyImage,
                contentDescription = "image_personal",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(imageSize)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            ) {
                it.error(R.drawable.ic_baseline_image)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .absolutePadding(0.dp,8.dp,0.dp,8.dp)
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = property.name ?: "",
                        style = ShackleHotelBuddyTheme.typography.bodyMediumBold,
                        color = ShackleHotelBuddyTheme.colors.black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = property.locationName ?: "",
                        style = ShackleHotelBuddyTheme.typography.bodyMedium,
                        color = ShackleHotelBuddyTheme.colors.grayText,
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = property.priceString ?: "",
                        style = ShackleHotelBuddyTheme.typography.bodyMediumBold,
                        color = ShackleHotelBuddyTheme.colors.black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = stringResource(R.string.content_desc_star),
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )
                        Text(
                            modifier = Modifier
                                .background(Color.Transparent),
                            text = "4.5",
                            style = ShackleHotelBuddyTheme.typography.bodyMedium,
                            color = ShackleHotelBuddyTheme.colors.black
                        )
                    }
            }
        }
}


/**
 *  TopAppBarView
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { TopAppBarTitleText() },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }
            ) {
                Icon(Icons.Outlined.ArrowBack, null, tint = Color.Black)
            }
        }
    )
}


/**
 *  TitleText
 */
@Composable
fun TopAppBarTitleText() {
    Text(
        text = stringResource(id = R.string.search_result),
        color = Color.Black,
        style = ShackleHotelBuddyTheme.typography.bodySemiBoldMedium
    )
}

@Preview
@Composable
private fun DetailViewPreview() {
    ShackleHotelBuddyTheme {
        DetailView(
            rememberNavController(),
            MutableStateFlow(listOf(Property("test","test","https://tinyurl.com/2bbjhyrr","$105 night","La, us","4.5"))).collectAsState(),
            MutableStateFlow(true).collectAsState()
        )
    }
}

@Preview
@Composable
private fun DetailItemView() {
    ShackleHotelBuddyTheme {
        PropertyItem(Property("test","test","https://tinyurl.com/2bbjhyrr","$105 night","La, us","4.5"))
    }
}