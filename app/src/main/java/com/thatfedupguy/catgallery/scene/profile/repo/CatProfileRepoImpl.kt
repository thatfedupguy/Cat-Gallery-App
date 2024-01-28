package com.thatfedupguy.catgallery.scene.profile.repo

import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo
import com.thatfedupguy.catgallery.scene.profile.data.CatProfileApi
import com.thatfedupguy.catgallery.utils.ApiResult
import com.thatfedupguy.catgallery.utils.ApiService
import javax.inject.Inject

class CatProfileRepoImpl @Inject constructor(
    private val catProfileApi: CatProfileApi,
    private val apiService: ApiService
) : CatProfileRepo {

    override suspend fun getCat(id: String): ApiResult<CatInfo> {
        return apiService.getApiResult {
            catProfileApi.getCat(id)
        }
    }
}