package com.thatfedupguy.catgallery.utils

import javax.inject.Inject

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}

class ApiService @Inject constructor() {
    suspend fun <T> getApiResult(callApi: suspend () -> T): ApiResult<T> {
        return try {
            val response = callApi()
            ApiResult.Success(response)
        } catch (e: Exception) {
            ApiResult.Error("Uh-oh! something went wrong\n${e.localizedMessage}")
        }
    }
}