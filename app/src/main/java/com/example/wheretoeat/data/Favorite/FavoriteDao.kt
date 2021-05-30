package com.example.wheretoeat.data.Favorite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun readAllFavorite() : LiveData<List<Favorite>>

    @Query("DELETE FROM favorite_table WHERE restaurant_id=:restaurant_id AND user_id=:user_id")
    suspend fun deleteFavorite(restaurant_id: String,user_id:Int)

    @Query("SELECT COUNT(*) FROM favorite_table")
    fun getCount(): LiveData<Int>

    @Query("SELECT * FROM favorite_table WHERE user_id=:id")
    fun readAllFavoritesByCurrentId(id : Int) : List<Favorite>
}