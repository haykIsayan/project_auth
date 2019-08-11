package com.example.project_auth.model

data class User (
        val userName: String,
        val firstName: String,
        val lastName: String,
        val password: String) {



    class UserResource(user: User): Resource.SuccessResource<User>(user)

}