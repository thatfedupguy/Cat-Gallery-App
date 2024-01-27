package com.thatfedupguy.catgallery.scene.data

import com.thatfedupguy.catgallery.utils.extns.or

data class GalleryResponseModel (
    val catList: List<CatInfo>?
)

data class CatInfo(
    val url: String?,
    val id: String?,
    val breeds: List<Breed>?
){
    fun getBreed(): String = breeds?.get(0)?.name.or("Not Found")
}

data class Breed(
    val name: String?
)