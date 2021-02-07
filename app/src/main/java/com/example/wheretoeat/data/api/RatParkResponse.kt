package com.example.wheretoeat.data.api

import com.example.wheretoeat.data.model.Restaurant

//envelope object, te retrieve the restaurants list
data class RatParkResponse (
    val restaurants: List<Restaurant>
){

}