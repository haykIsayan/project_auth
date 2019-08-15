package com.example.project_auth.presentation.presenters

import androidx.lifecycle.*
import com.example.project_auth.domain.AuthUseCase
import com.example.project_auth.model.Resource
import com.example.project_auth.presentation.controllers.ViewController

abstract class AuthPresenter<T: ViewController>(protected var viewController: T?): LifecycleObserver {

    fun init() {
        viewController?.lifecycle?.addObserver(this)
    }

    protected fun <R> executeUseCase(authUseCase: AuthUseCase<R>,
                                     onSuccess: (data: R) -> Unit,
                                     onPending: (pendingMessage: String) -> Unit) {

        viewController?.apply {
            authUseCase.execute().observe(this, Observer {
                when (it) {
                    is Resource.SuccessResource -> onSuccess(it.data)
                    is Resource.PendingResource -> onPending(it.message)
                    is Resource.FailResource -> onFailure(it)
                }
            })
        }
    }

    protected open fun <F> onFailure(failResource: Resource.FailResource<F>) {
        failResource.throwable?.apply {
            viewController?.onError(this)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected fun onDestroyPresenter() {
        viewController?.lifecycle?.removeObserver(this)
        viewController = null
    }

}