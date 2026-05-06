package com.example.popmartcatalog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopMart(
    val name: String,
    val series: String,
    val price: Int,
    val status: String,
    val image: Int
) : Parcelable