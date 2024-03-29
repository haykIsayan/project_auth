package com.example.project_auth.domain

import com.example.project_auth.NoUserFoundResource
import com.example.project_auth.PasswordIncorrectResource
import com.example.project_auth.data.UserRepository
import com.example.project_auth.model.Resource
import com.example.project_auth.model.User

class LoginUserInteractor(private val userName: String, private val password: String,
                          userRepository: UserRepository): AuthInteractor<User>(userRepository) {


    override suspend fun run(): Resource<User> {
        return try {
            val user = userRepository.getUser(userName)
            validateUser(user)
        } catch (throwable: Throwable) {
            Resource.fail(throwable)
        }
    }

    private fun validateUser(user: User?) = when {
        user == null -> NoUserFoundResource()
        user.password != password -> PasswordIncorrectResource()
        else -> Resource.success(user)
    }
}