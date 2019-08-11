package com.example.project_auth.data

import androidx.lifecycle.LiveData
import com.example.project_auth.model.User

interface AuthRepository {

    fun login(userName: String, password: String): LiveData<User>

    fun register(user: User): LiveData<User>

}