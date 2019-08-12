package com.example.project_auth

import com.example.project_auth.model.User

object AuthUtils {


    fun getDummieUser() = User("haykIsayan",
            "Hayk",
            "Isayan",
            "1234")

    const val DATABASE_NAME = "Auth.Database"
    const val TEST_DATABASE_NAME = "Test.Database"
}