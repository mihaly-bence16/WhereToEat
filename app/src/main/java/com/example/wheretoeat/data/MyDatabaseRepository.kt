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
    suspend fun addFavorite(favorite: Favorite){
        favoriteDao.addFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite){
        favoriteDao.deleteFavorite(favorite)
    }
    fun getCountFavorite() : LiveData<Int > {
        return favoriteDao.getCount()
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

    suspend fun deleteCurrentUser(currentUser: CurrentUser){
        currentUserDao.deleteCurrentUser(currentUser)
    }
}