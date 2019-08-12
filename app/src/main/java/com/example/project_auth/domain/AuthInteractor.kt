package com.example.project_auth.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project_auth.data.UserRepository
import com.example.project_auth.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class AuthInteractor<T>(protected val userRepository: UserRepository): AuthUseCase<T> {

    private val mInteractorData = MutableLiveData<Resource<T>>()

    private var mEnablePending = false

    fun enablePending(enablePending: Boolean) = apply {
        this.mEnablePending = enablePending
    }

    override fun execute(): LiveData<Resource<T>> {
        if (mEnablePending) { mInteractorData.value = Resource.pending("") }

        val asyncJob = GlobalScope.async(Dispatchers.IO) { run() }

        GlobalScope.launch(Dispatchers.Main) {
            mInteractorData.value = asyncJob.await()
        }
        return mInteractorData
    }

    override suspend fun test(): LiveData<Resource<T>> {
        if (mEnablePending) { mInteractorData.value = Resource.pending("") }
        mInteractorData.value = run()
        return mInteractorData
    }

    abstract suspend fun run(): Resource<T>

}