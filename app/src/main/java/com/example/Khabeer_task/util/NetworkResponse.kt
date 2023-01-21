package com.example.Khabeer_task.util

sealed class NetworkResponse<out T> {

    data class Success<T>(val data: T?) : NetworkResponse<T>()

    data class Error(val isNetworkError: Boolean, val errorMessage: String? = null) :
        NetworkResponse<Nothing>()

    data class Loading<T>(val data: T? = null) : NetworkResponse<T>()
}
