package com.example.project_auth.presentation

import com.example.project_auth.domain.LoginInteractor

class LoginPresenter(loginViewOperator: LoginViewController): AuthPresenter<LoginViewController>(loginViewOperator) {

    fun onLoginClick(userName: String, password: String) {

        executeUseCase(LoginInteractor(userName, password), {
            viewController?.onLoginSuccess(it)
        }, {
            viewController?.showLoading(it)
        }, {
            viewController?.onLoginFailed(it)
        })
    }
}