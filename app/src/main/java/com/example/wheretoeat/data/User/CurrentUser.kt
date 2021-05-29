package com.example.wheretoeat.data.User

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "current_user")
data class CurrentUser(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val current_user_id: Int,
    val date: Long
) : Parcelable