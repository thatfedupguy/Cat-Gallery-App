package com.thatfedupguy.catgallery.utils

import com.thatfedupguy.catgallery.utils.extns.or
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
            ApiResult.Error(e.message.or("Something went wrong"))
        }
    }
}