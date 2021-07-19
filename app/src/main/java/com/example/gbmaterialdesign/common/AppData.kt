package com.example.gbmaterialdesign.common

sealed class AppData<T> {
    data class Success<T>(val data: T): AppData<T>()
    data class Error<T>(val error: Throwable): AppData<T>()
    class Loading<T>: AppData<T>()
}
