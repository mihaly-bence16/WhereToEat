package com.example.wheretoeat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wheretoeat.data.Favorite.Favorite
import com.example.wheretoeat.data.Favorite.FavoriteDao
import com.example.wheretoeat.data.User.CurrentUser
import com.example.wheretoeat.data.User.CurrentUserDao
import com.example.wheretoeat.data.User.User
import com.example.wheretoeat.data.User.UserDao

@Database(entities = [User::class, Favorite::class,CurrentUser::class], version = 2, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun favoriteDao() : FavoriteDao
    abstract fun currentUserDao() : CurrentUserDao

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase?= null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance= INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "user_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}