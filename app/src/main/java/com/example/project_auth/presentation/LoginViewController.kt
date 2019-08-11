package com.example.project_auth.presentation

import com.example.project_auth.model.User

interface LoginViewController: ViewController {

    fun onLoginSuccess(user: User)

    fun showLoading(message: String)

    fun onLoginFailed(throwable: Throwable)

}