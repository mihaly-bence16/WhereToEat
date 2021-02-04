package com.example.wheretoeat

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//evry application that uses dagger hilt needs and application class
@HiltAndroidApp
class RestaurantApplication :Application(){
}