package com.example.project_auth.data

import android.app.Application
import com.example.project_auth.AuthUtils
import com.example.project_auth.model.User

class UserDataSource(private val application: Application): UserRepository {

    private var isTestable = false

    override suspend fun clearRepository() {
        getDatabaseInstance()?.userDao()?.deleteAll()
    }

    override suspend fun getUsers(): List<User>? {
        return getDatabaseInstance()?.userDao()?.getUsers()
    }

    override suspend fun getUser(userName: String): User? {
        return getDatabaseInstance()?.userDao()?.getUserByUsername(userName)
    }

    override suspend fun insertUser(user: User): Long? {
        return getDatabaseInstance()?.userDao()?.insertUser(user)
    }

    fun setTestable() = apply {
        isTestable = true
    }

    private fun getDatabaseInstance() = if (!isTestable) {
        UserDatabase.getInstance(application)
    } else {
        UserDatabase.getTestableInstance(application)
    }
}