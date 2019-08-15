package com.example.project_auth.presentation.presenters

import android.app.Application
import com.example.project_auth.AlreadyRegisteredResource
import com.example.project_auth.data.UserDataSource
import com.example.project_auth.domain.RegisterUserInteractor
import com.example.project_auth.model.Resource
import com.example.project_auth.model.User
import com.example.project_auth.presentation.controllers.RegisterViewController

class RegisterPresenter(registerViewController: RegisterViewController,
                        private val application: Application):
        AuthPresenter<RegisterViewController>(registerViewController) {


    fun onRegisterClick(userName: String, firstName: String,
                        lastName: String, password: String) {

        val user = User(userName, firstName, lastName, password)

        executeUseCase(RegisterUserInteractor(user, UserDataSource(application)), {
            viewController?.onRegisterSuccess(it)
        }, {
            viewController?.onRegisterPending()
        })
    }

    override fun <F> onFailure(failResource: Resource.FailResource<F>) {
        when (failResource) {
            is AlreadyRegisteredResource -> viewController?.onAlreadyRegistered()
            else -> super.onFailure(failResource)
        }
    }
}