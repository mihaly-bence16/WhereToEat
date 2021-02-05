package com.example.wheretoeat.data.model

import com.example.wheretoeat.data.api.RatParkApi
import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

private const val RESTAURANT_STARTING_PAGE_INDEX=1

//we do not inject this one because we do not know the query at compile time
class RestaurantPagingSource(
    private val ratParkApi: RatParkApi,
    private val city: String
) : PagingSource<Int, Restaurant>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Restaurant> {
        val position = params.key ?: RESTAURANT_STARTING_PAGE_INDEX


        return try {
            val response = ratParkApi.getRestaurants(city)
            val restaurants = response.restaurants
            LoadResult.Page(
                data = restaurants,
                prevKey = if (position== RESTAURANT_STARTING_PAGE_INDEX) null else position -1,
                nextKey = if (restaurants.isEmpty()) null else position +1
            )
        }
        catch (exception:IOException){
            LoadResult.Error(exception)
        }
        catch (exception:HttpException){
            LoadResult.Error(exception)
        }
    }

}