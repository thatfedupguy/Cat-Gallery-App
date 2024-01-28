package com.thatfedupguy.catgallery.utils.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.thatfedupguy.catgallery.ui.theme.type.interSemiBold

@Composable
fun ErrorText(
    modifier: Modifier = Modifier,
    text: String = "Uh-Oh Something went wrong!",
    style: TextStyle = interSemiBold.titleMedium
) {
    Box(modifier = modifier) {
        Text(
            modifier = modifier,
            text = text,
            style = style
        )
    }
}