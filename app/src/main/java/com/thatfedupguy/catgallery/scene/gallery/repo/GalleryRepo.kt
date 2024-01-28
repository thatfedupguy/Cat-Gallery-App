package com.thatfedupguy.catgallery.scene.gallery.repo

import androidx.paging.PagingData
import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo
import kotlinx.coroutines.flow.Flow

interface GalleryRepo {

    fun getCats(): Flow<PagingData<CatInfo>>
}