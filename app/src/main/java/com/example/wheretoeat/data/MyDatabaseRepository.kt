package com.example.wheretoeat.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.wheretoeat.data.Favorite.Favorite
import com.example.wheretoeat.data.Favorite.FavoriteDao
import com.example.wheretoeat.data.User.CurrentUser
import com.example.wheretoeat.data.User.CurrentUserDao
import com.example.wheretoeat.data.User.User
import com.example.wheretoeat.data.User.UserDao

class MyDatabaseRepository(private val userDao: UserDao, private val favoriteDao: FavoriteDao, private val currentUserDao: CurrentUserDao) {

    val readAllUsers: LiveData<List<User>> = userDao.readAllUsers()
    val readAllFavorite : LiveData<List<Favorite>> = favoriteDao.readAllFavorite()

    //Favorite
    fun addFavorite(favorite: Favorite){
        favoriteDao.addFavorite(favorite)
    }

    suspend fun deleteFavorite(restaurant_id: String, user_id : Int){
        favoriteDao.deleteFavorite(restaurant_id, user_id)
    }
    fun getCountFavorite() : LiveData<Int > {
        return favoriteDao.getCount()
    }

    @WorkerThread
    fun readAllFavoritesByCurrentId(id : Int) : List<Favorite>{
        return favoriteDao.readAllFavoritesByCurrentId(id)
    }

    //User
    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    fun getCountUser(): LiveData<Int> {
        return userDao.getCount()
    }

    @WorkerThread
    fun getUserById(id : Int): User {
        return userDao.getUserById(id)
    }

    //Current user
    @WorkerThread
    fun getCurrentUserId() : Int {
        return currentUserDao.getCurrentId()
    }

    suspend fun addCurrentUser(currentUser: CurrentUser){
        currentUserDao.addCurrentUser(currentUser)
    }

    suspend fun deleteCurrentUser(){
        currentUserDao.deleteCurrentUser()
    }
}