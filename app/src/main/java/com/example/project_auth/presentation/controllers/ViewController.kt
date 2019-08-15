package com.example.project_auth.presentation.controllers

import androidx.lifecycle.LifecycleOwner

interface ViewController: LifecycleOwner {
    fun onError(throwable: Throwable)
}