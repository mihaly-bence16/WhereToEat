package com.example.wheretoeat.data.User

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllUsers(): LiveData<List<User>>


    @Query("SELECT COUNT(*) FROM user_table")
    fun getCount(): LiveData<Int>
}