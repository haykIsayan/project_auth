package com.example.project_auth.data

import androidx.lifecycle.LiveData
import com.example.project_auth.model.User

class AuthDataSource: AuthRepository {

    override fun login(userName: String, password: String): LiveData<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun register(user: User): LiveData<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}