package com.example.wheretoeat.data.repository

import com.example.wheretoeat.data.api.RatParkApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepository @Inject constructor (private val ratParkApi: RatParkApi) {
}