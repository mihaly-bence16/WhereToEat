package com.example.wheretoeat.ui.list

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.wheretoeat.data.repository.RestaurantRepository

class ListViewModel @ViewModelInject constructor(
    private val repository: RestaurantRepository,
    @Assisted state: SavedStateHandle
):ViewModel() {
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val restaurants = currentQuery.switchMap {
        queryString -> repository.getCityResults(queryString).cachedIn(viewModelScope)
    }

    fun getRestaurants(city : String){
        currentQuery.value= city
    }

    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = ""
    }
}