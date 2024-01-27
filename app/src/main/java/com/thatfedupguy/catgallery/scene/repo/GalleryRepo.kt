package com.thatfedupguy.catgallery.scene.repo

import androidx.paging.PagingData
import com.thatfedupguy.catgallery.scene.data.CatInfo
import kotlinx.coroutines.flow.Flow

interface GalleryRepo {

    fun getCats(): Flow<PagingData<CatInfo>>
}