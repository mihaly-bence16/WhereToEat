package com.example.wheretoeat.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    val id: String,
    val name: String?,
    val address: String?,
    val city: String?,
    val state: String?,
    val area: String?,
    val postal_code: String?,
    val country: String?,
    val phone: String?,
    val lat: Int?,
    val lng: Int?,
    val price: Int?,
    val reserve_url: String?,
    val mobile_reserve_url: String?,
    val image_url: String?
): Parcelable