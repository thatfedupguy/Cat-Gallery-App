package com.thatfedupguy.catgallery.utils.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.thatfedupguy.catgallery.R

@Composable
fun AppImageView(
    modifier: Modifier = Modifier,
    model: Any?,
    contentScale: ContentScale = ContentScale.Crop,
    @DrawableRes placeHolder: Int = R.drawable.ic_app_logo
) {
    Box(modifier = modifier.background(color = Color.White)) {
        var showPlaceHolder by remember {
            mutableStateOf(true)
        }
        if (showPlaceHolder) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = placeHolder),
                contentDescription = null,
                contentScale = contentScale,
            )
        }
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = model,
            contentScale = contentScale,
            contentDescription = null,
            onState = {
                showPlaceHolder = it !is AsyncImagePainter.State.Success
            }
        )
    }
}