package com.example.shacklehotelbuddy.feature_hotel_search.presentation.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.feature_hotel_search.domain.model.Property
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme


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
    Box(
        modifier = Modifier.padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            GlideImage(
                model = property.propertyImage,
                contentDescription = "image_personal",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(imageSize)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            ) {
                it.error(R.drawable.ic_baseline_image)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .padding(end = 32.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = property.name ?: "",
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = property.locationName ?: "",
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.grayText,
                    maxLines = 10,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = property.priceString ?: "",
                    style = ShackleHotelBuddyTheme.typography.bodyMedium,
                    color = ShackleHotelBuddyTheme.colors.black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "star",
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
}


/**
 *  TopAppBarView
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarView(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { TopAppBarTitleText() },
        modifier = Modifier
            .padding(8.dp),
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
