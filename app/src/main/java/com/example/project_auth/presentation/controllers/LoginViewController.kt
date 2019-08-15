package com.example.project_auth.presentation.controllers

import com.example.project_auth.model.User

interface LoginViewController: ViewController {

    fun onLoginSuccess(user: User)

    fun showLoading(message: String)

    fun onNoUserFound()

    fun onPasswordIncorrect()

}