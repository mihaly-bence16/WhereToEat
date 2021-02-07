package com.example.wheretoeat.data.User

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllUsers: LiveData<List<User>>
    private val repository: UserRepository
    val getCount: LiveData<Int>

    init {
        val userDao = UserDatebase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllUsers = repository.readAllUsers
        getCount = repository.getCount()
    }


    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) { repository.addUser(user) }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateUser(user) }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteUser(user) }
    }
}
