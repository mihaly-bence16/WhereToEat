package com.example.wheretoeat.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.wheretoeat.data.repository.RestaurantRepository

class ListViewModel @ViewModelInject constructor(
    private val repository: RestaurantRepository
):ViewModel() {
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val restaurants = currentQuery.switchMap {
        queryString -> repository.getCityResults(queryString).cachedIn(viewModelScope)
    }

    fun getRestaurants(city : String){
        currentQuery.value= city
    }

    companion object{
        private const val DEFAULT_QUERY = ""
    }
}