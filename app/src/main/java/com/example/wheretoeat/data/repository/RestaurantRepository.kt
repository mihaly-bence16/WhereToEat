package com.example.wheretoeat.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.wheretoeat.data.api.RatParkApi
import com.example.wheretoeat.data.model.RestaurantPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepository @Inject constructor (private val ratParkApi: RatParkApi) {

    fun getCityResults(city : String) =
        Pager(
            config = PagingConfig(
                pageSize = 25,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RestaurantPagingSource(ratParkApi,city)}
        ).liveData
}