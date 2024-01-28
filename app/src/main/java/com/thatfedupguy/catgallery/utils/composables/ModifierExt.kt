package com.thatfedupguy.catgallery.utils.composables

import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.thatfedupguy.catgallery.ui.theme.darkGrey

@Composable
fun Modifier.shimmerPlaceholder(
    color: Color = darkGrey,
    visible: Boolean = true,
    highlight: PlaceholderHighlight = PlaceholderHighlight.shimmer(
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, delayMillis = 100)
        )
    )
) = this.placeholder(
    color = color,
    visible = visible,
    highlight = highlight
)

@Composable
fun Modifier.appClickable(
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    onClick: () -> Unit
) = this.clickable(
    interactionSource = interactionSource,
    indication = null,
    onClick = onClick
)