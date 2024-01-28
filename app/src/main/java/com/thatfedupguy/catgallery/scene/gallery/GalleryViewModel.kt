package com.thatfedupguy.catgallery.scene.gallery

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo
import com.thatfedupguy.catgallery.scene.gallery.repo.GalleryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class GalleryViewModel @Inject constructor(
    repo: GalleryRepo
) : ViewModel() {
    var cats: Flow<PagingData<CatInfo>> = repo.getCats()
}