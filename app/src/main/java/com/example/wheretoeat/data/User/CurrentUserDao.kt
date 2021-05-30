package com.example.wheretoeat.data.User

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CurrentUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrentUser(currentUser: CurrentUser)

    @Query("SELECT current_user_id FROM current_user ORDER BY date DESC")
    fun getCurrentId() : Int

    @Query("DELETE  FROM current_user WHERE id not in(SELECT id FROM current_user ORDER BY date desc LIMIT 1)")
    suspend fun deleteCurrentUser()
}