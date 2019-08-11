package com.example.project_auth.domain

import androidx.lifecycle.LiveData
import com.example.project_auth.model.Resource

interface AuthUseCase<T> {

    fun execute(): LiveData<Resource<T>>

}