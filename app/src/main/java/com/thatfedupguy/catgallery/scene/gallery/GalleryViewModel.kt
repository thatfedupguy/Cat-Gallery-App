package com.thatfedupguy.catgallery.scene.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo
import com.thatfedupguy.catgallery.scene.gallery.repo.GalleryRepo
import com.thatfedupguy.catgallery.utils.annotations.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repo: GalleryRepo,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    var cats: Flow<PagingData<CatInfo>>? = null

    init {
        fetchCats()
    }

    private fun fetchCats() {
        viewModelScope.launch {
            cats = repo.getCats().flowOn(ioDispatcher)
        }
    }
}