package com.example.project_auth.domain

import androidx.lifecycle.MutableLiveData
import com.example.project_auth.data.UserRepository
import com.example.project_auth.model.Resource

abstract class UserInteractor<T>(
        protected val userRepository: UserRepository): AuthUseCase<T> {

    protected var mEnablePending = true

    protected val mInteractorData = MutableLiveData<Resource<T>>()


    fun invoke() {

    }




    fun notifyPending(enablePending: Boolean) = apply {
        this.mEnablePending = enablePending
    }

}