package com.thatfedupguy.catgallery.utils.composables


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.thatfedupguy.catgallery.ui.theme.AppTheme
import com.thatfedupguy.catgallery.ui.theme.type.interSemiBold
import com.thatfedupguy.catgallery.utils.annotations.CatGalleryPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                text = title,
                style = interSemiBold.headlineMedium
            )
        })
}

@CatGalleryPreview
@Composable
fun TopAppBarPreview() {
    AppTheme {
        Surface {
            AppBar(title = "Cat Gallery")
        }
    }
}