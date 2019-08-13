package com.example.project_auth.presentation

import androidx.lifecycle.*
import com.example.project_auth.domain.AuthUseCase
import com.example.project_auth.model.Resource

abstract class AuthPresenter<T: ViewController>(protected var viewController: T?): LifecycleObserver {

    fun init() {
        viewController?.lifecycle?.addObserver(this)
    }

    protected fun <T> executeUseCase(authUseCase: AuthUseCase<T>,
                                     onSuccess: (data: T) -> Unit,
                                     onPending: (pendingMessage: String) -> Unit,
                                     onFailure: (throwable: Throwable?) -> Unit) {

        viewController?.apply {
            authUseCase.execute().observe(this, Observer {
                when (it) {
                    is Resource.SuccessResource -> onSuccess(it.data)
                    is Resource.PendingResource -> onPending(it.message)
                    is Resource.FailResource -> onFailure(it.throwable)
                }
            })
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected fun onDestroyPresenter() {
        viewController?.lifecycle?.removeObserver(this)
        viewController = null
    }

}