package com.thatfedupguy.catgallery.scene.api

import com.thatfedupguy.catgallery.scene.data.CatInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("v1/images/search")
    suspend fun getCats(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<CatInfo>>
}