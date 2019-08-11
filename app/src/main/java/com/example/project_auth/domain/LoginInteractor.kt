package com.example.project_auth.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project_auth.model.Resource
import com.example.project_auth.model.User

class LoginInteractor(private val userName: String,
                      private val password: String): AuthUseCase<User> {

    override fun execute(): LiveData<Resource<User>> {
        return MutableLiveData()
    }
}