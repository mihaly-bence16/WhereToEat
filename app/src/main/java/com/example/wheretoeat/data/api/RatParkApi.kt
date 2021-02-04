package com.example.wheretoeat.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface RatParkApi {

    companion object{
        const val BASE_URL="https://ratpark-api.imok.space/"
    }

    @GET("restaurants")
    suspend fun getRestaurants(
        @Query ("city") city: String
    ):RatParkResponse
}