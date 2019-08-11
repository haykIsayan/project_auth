package com.example.project_auth.model

sealed class Resource<T> constructor(val status: Status = Status.PENDING
) {

    open class SuccessResource<T>(val data: T): Resource<T>(Status.SUCCESS)

    data class PendingResource<T>(val message: String): Resource<T>()

    data class FailResource<T>(val throwable: Throwable): Resource<T>(Status.FAIL)

    companion object {
        fun <T> success(data: T) = SuccessResource(data)
        fun <T> fail(throwable: Throwable) = FailResource<T>(throwable)
        fun <T> pending(message: String) = PendingResource<T>(message)
    }

    enum class Status {
        SUCCESS, PENDING, FAIL
    }
}