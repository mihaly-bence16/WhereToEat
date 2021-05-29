package com.example.wheretoeat.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wheretoeat.data.Favorite.Favorite
import com.example.wheretoeat.data.User.CurrentUser
import com.example.wheretoeat.data.User.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyDatabaseViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MyDatabaseRepository

    val readAllUsers: LiveData<List<User>>
    val readAllFavorite : LiveData<List<Favorite>>
    val getCountUser: LiveData<Int>
    val getCountFavorite : LiveData<Int>


    init {
        val userDao = MyDatabase.getDatabase(application).userDao()
        val favoriteDao = MyDatabase.getDatabase(application).favoriteDao()
        val currentUserDao = MyDatabase.getDatabase(application).currentUserDao()
        repository = MyDatabaseRepository(userDao,favoriteDao,currentUserDao)

        readAllFavorite=repository.readAllFavorite
        readAllUsers = repository.readAllUsers
        getCountFavorite= repository.getCountFavorite()
        getCountUser = repository.getCountUser()
    }

    //favorite
    fun addFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) { repository.addFavorite(favorite) }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteFavorite(favorite) }
    }

    //user
    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) { repository.addUser(user) }
    }


    fun getUserById(id :Int) : LiveData<User>{
        val result = MutableLiveData<User>()
        viewModelScope.launch {
            val data = repository.getUserById(id)
            result.value = data
        }
        return result
    }

    fun getCurrentUserid() : LiveData<Int> {
        val result = MutableLiveData<Int>()
        viewModelScope.launch {
            val data = repository.getCurrentUserId()
            result.value = data
        }
        return result
    }

    fun addCurrentUser(currentUser: CurrentUser){
        viewModelScope.launch(Dispatchers.IO) { repository.addCurrentUser(currentUser) }
    }

    fun deleteCurrentUser(currentUser: CurrentUser){
        viewModelScope.launch(Dispatchers.IO) { repository.deleteCurrentUser(currentUser) }
    }
}
