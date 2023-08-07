package com.example.shacklehotelbuddy.feature_hotel_search.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchQueryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "checked_in_date")
    val checkedInDate: String?,
    @ColumnInfo(name = "checked_out_date")
    val checkedOutDate: String?,
    @ColumnInfo(name = "adults")
    val adults: Int?,
    @ColumnInfo(name = "children")
    val children: Int?,
    @ColumnInfo(name = "date_added")
    val date: Long
)