package com.example.wheretoeat.data.Favorite

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.wheretoeat.data.User.User
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_table")
data class Favorite (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val restaurant_id: String,
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val area: String,
    val postal_code: String,
    val country: String,
    val phone: String,
    val lat: Double,
    val lng: Double,
    val price: Int,
    val reserve_url: String,
    val mobile_reserve_url: String,
    val image_url: String,
    val user_id : Int
) : Parcelable