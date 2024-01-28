package com.thatfedupguy.catgallery.scene.profile.repo

import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo
import com.thatfedupguy.catgallery.utils.ApiResult

interface CatProfileRepo {

    suspend fun getCat(id: String): ApiResult<CatInfo>
}