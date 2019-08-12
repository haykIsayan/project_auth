package com.example.project_auth.presentation

import android.app.Application
import com.example.project_auth.data.UserDataSource
import com.example.project_auth.domain.LoginInteractor

class LoginPresenter(loginViewOperator: LoginViewController,
                     private val application: Application): AuthPresenter<LoginViewController>(loginViewOperator) {

    fun onLoginClick(userName: String, password: String) {

        executeUseCase(LoginInteractor(userName, password, "sdfgs",
                UserDataSource(application)), {
            viewController?.onLoginSuccess(it)
        }, {
            viewController?.showLoading(it)
        }, {
            viewController?.onLoginFailed(it)
        })
    }
}