package com.thatfedupguy.catgallery.scene.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.thatfedupguy.catgallery.ui.theme.lightGrey
import com.thatfedupguy.catgallery.utils.annotations.CatGalleryPreview
import com.thatfedupguy.catgallery.utils.composables.shimmerPlaceholder

@CatGalleryPreview
@Composable
fun GalleryShimmer() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(6) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        color = lightGrey,
                        shape = RoundedCornerShape(corner = CornerSize(8.dp))
                    )
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .shimmerPlaceholder()
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Box(
                        modifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .shimmerPlaceholder()
                    )
                    Box(
                        modifier = Modifier
                            .height(32.dp)
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .shimmerPlaceholder()
                    )
                }
            }
        }
    }
}