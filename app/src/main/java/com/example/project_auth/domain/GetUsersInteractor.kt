package com.example.project_auth.domain

import com.example.project_auth.UserListEmptyResource
import com.example.project_auth.data.UserRepository
import com.example.project_auth.model.Resource
import com.example.project_auth.model.User

class GetUsersInteractor(userRepository: UserRepository)
    : AuthInteractor<List<User>>(userRepository) {

    override suspend fun run(): Resource<List<User>> {
        val userList = userRepository.getUsers()

        if (userList == null || userList.isEmpty()) {
            return UserListEmptyResource()
        }
        return Resource.success(userList)
    }
}