package com.example.project_auth.domain.temp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project_auth.data.UserRepository
import com.example.project_auth.model.Resource
import com.example.project_auth.model.User
import kotlinx.coroutines.*

class LoginInteractor(private val userName: String,
                      private val password: String,
                      private val loadingMessage: String,
                      userRepository: UserRepository): UserInteractor<User>(userRepository) {


    private fun loginUser(jobDispatcher: CoroutineDispatcher): LiveData<Resource<User>> {
        val data = MutableLiveData<Resource<User>>()

        if (mEnablePending) { data.value = Resource.pending(loadingMessage) }

        val getUserAsyncJob = GlobalScope.async(jobDispatcher) { userRepository.getUser(userName) }

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val user = getUserAsyncJob.await()
                data.value = validateUser(user)
            } catch (throwable: Throwable) {
                data.value = Resource.fail(throwable)
            }
        }
        return data
    }

    private fun validateUser(user: User?) =
            if (user == null || user.password != password) {
                Resource.fail<User>(Throwable("FUCK"))
            } else {
                Resource.success(user)
            }

    override fun execute(): LiveData<Resource<User>> {
       return loginUser(Dispatchers.IO)
    }

    override suspend fun test(): LiveData<Resource<User>> {
        if (mEnablePending) {mInteractorData.value = Resource.pending("")}


        return MutableLiveData()
    }
}