package com.example.project_auth.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project_auth.AuthUtils
import com.example.project_auth.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: UserDatabase? = null

        fun getInstance(application: Application): UserDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                        application,
                        UserDatabase::class.java,
                        AuthUtils.DATABASE_NAME)
                        .fallbackToDestructiveMigration().build()
            }
            return instance
        }

        fun getTestableInstance(application: Application) =
                Room.inMemoryDatabaseBuilder(
                        application, UserDatabase::class.java)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries().build()
    }



}