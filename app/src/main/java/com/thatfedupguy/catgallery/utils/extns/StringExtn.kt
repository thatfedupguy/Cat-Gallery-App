package com.thatfedupguy.catgallery.utils.extns

fun String?.or(other: String): String = this ?: other

fun String?.orDefault(): String = this ?: "-"