package com.example.wheretoeat.data.Favorite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun readAllFavorite() : LiveData<List<Favorite>>

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT COUNT(*) FROM favorite_table")
    fun getCount(): LiveData<Int>
}