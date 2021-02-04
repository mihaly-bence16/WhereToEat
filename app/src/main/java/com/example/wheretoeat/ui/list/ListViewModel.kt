package com.example.wheretoeat.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.wheretoeat.data.repository.RestaurantRepository

class ListViewModel @ViewModelInject constructor(
    private val repository: RestaurantRepository
):ViewModel() {
}