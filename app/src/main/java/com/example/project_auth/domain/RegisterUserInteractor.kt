package com.example.project_auth.domain

import com.example.project_auth.data.UserRepository
import com.example.project_auth.model.Resource
import com.example.project_auth.model.User

class RegisterUserInteractor(
        private val user: User,
        userRepository: UserRepository): AuthInteractor<User>(userRepository) {

    override suspend fun run(): Resource<User> {
        try {
            val user = userRepository.getUser(user.userName)

            if (user == null) {
                userRepository.insertUser(this.user)
                return Resource.success(this.user)
            }
            return Resource.fail(Throwable("Already Added"))
        } catch (throwable: Throwable) {
            return Resource.fail(throwable)
        }
    }
}