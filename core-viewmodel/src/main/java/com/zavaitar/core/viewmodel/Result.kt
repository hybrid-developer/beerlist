package com.zavaitar.core.viewmodel

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: String? = null) : Result<Nothing>()

    companion object {
        const val NO_NETWORK = "No network"
    }
}
