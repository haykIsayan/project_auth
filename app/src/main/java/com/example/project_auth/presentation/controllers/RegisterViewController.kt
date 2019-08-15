package com.example.project_auth.presentation.controllers

import com.example.project_auth.model.User

interface RegisterViewController: ViewController {

    fun onRegisterSuccess(user: User)

    fun onRegisterPending()

    fun onAlreadyRegistered()

}