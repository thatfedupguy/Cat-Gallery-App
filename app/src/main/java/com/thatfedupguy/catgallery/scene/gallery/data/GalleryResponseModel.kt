package com.thatfedupguy.catgallery.scene.gallery.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import com.thatfedupguy.catgallery.utils.extns.or
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CatInfo(
    val url: String?,
    val id: String?,
    val breeds: List<Breed>?
) : Parcelable {
    fun getBreed(): String = breeds?.get(0)?.name.or("Not Found")
}

@Parcelize
@JsonClass(generateAdapter = true)
data class Breed(
    val name: String?
) : Parcelable