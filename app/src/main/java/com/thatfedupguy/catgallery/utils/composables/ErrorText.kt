package com.thatfedupguy.catgallery.utils.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.thatfedupguy.catgallery.ui.theme.type.interSemiBold

@Composable
fun ErrorText(
    modifier: Modifier = Modifier,
    text: String = "Uh-Oh Something went wrong!",
    style: TextStyle = interSemiBold.titleMedium.copy(textAlign = TextAlign.Center)
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = text,
            style = style
        )
    }
}