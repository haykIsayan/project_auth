package com.example.project_auth.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project_auth.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM user_table WHERE user_name = :userName")
    suspend fun getUserByUsername(userName: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}