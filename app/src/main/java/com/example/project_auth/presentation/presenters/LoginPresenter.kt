package com.example.project_auth.presentation.presenters

import android.app.Application
import com.example.project_auth.NoUserFoundResource
import com.example.project_auth.PasswordIncorrectResource
import com.example.project_auth.data.UserDataSource
import com.example.project_auth.domain.LoginUserInteractor
import com.example.project_auth.domain.temp.LoginInteractor
import com.example.project_auth.model.Resource
import com.example.project_auth.model.User
import com.example.project_auth.presentation.controllers.LoginViewController

class LoginPresenter(loginViewOperator: LoginViewController, private val application: Application):
        AuthPresenter<LoginViewController>(loginViewOperator) {

    fun onLoginClick(userName: String, password: String) {

        executeUseCase(LoginUserInteractor(userName, password,
                UserDataSource(application)), {
            viewController?.onLoginSuccess(it)
        }, {
            viewController?.showLoading(it)
        })
    }

    override fun <F> onFailure(failResource: Resource.FailResource<F>) {
        when (failResource) {
            is NoUserFoundResource -> viewController?.onNoUserFound()
            is PasswordIncorrectResource -> viewController?.onPasswordIncorrect()
            else -> super.onFailure(failResource)
        }
    }
}