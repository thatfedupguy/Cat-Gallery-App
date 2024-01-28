package com.thatfedupguy.catgallery.scene.gallery

import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo

sealed interface GalleryDirections {
    data class CatProfile(val catInfo: CatInfo) : GalleryDirections
}