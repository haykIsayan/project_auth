package com.example.project_auth.data

import com.example.project_auth.model.User

interface UserRepository {

    suspend fun getUser(userName: String): User?

    suspend fun insertUser(user: User): Long?

    suspend fun getUsers(): List<User>?

    suspend fun clearRepository()
}