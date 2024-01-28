package com.thatfedupguy.catgallery.scene.profile.data

import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface CatProfileApi {
    @GET("v1/images/{catId}")
    suspend fun getCat(
        @Path("catId") id: String
    ): CatInfo
}