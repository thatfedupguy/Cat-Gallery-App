package com.thatfedupguy.catgallery.scene.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.thatfedupguy.catgallery.R
import com.thatfedupguy.catgallery.scene.data.CatInfo
import com.thatfedupguy.catgallery.ui.theme.type.interSemiBold
import com.thatfedupguy.catgallery.utils.annotations.CatGalleryPreview
import com.thatfedupguy.catgallery.utils.composables.PagingLoadingComposable
import com.thatfedupguy.catgallery.utils.extns.orDefault
import kotlinx.coroutines.flow.Flow

@Composable
fun GalleryScreen(
    galleryItems: Flow<PagingData<CatInfo>>
) {
    val catInfos = galleryItems.collectAsLazyPagingItems()
    val itemCount = catInfos.itemCount
    val loadState = catInfos.loadState
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (itemCount == 0 && loadState.append is LoadState.NotLoading && loadState.append.endOfPaginationReached) {
            Text(stringResource(R.string.no_cats_found), style = interSemiBold.titleMedium)
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = rememberLazyListState(),
                content = {
                    items(count = itemCount) { index ->
                        val catInfo = catInfos[index]
                        catInfo?.let {
                            GalleryItem(item = it)
                        }
                    }
                    item {
                        PagingLoadingComposable(loadState = loadState)
                    }
                }
            )
        }
    }
}

@Composable
fun GalleryItem(
    item: CatInfo
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(152.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = item.url,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.cat_image)
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = stringResource(R.string.id, item.id.orDefault()))
            Text(text = stringResource(R.string.breed, item.getBreed()))
        }
    }
}

@Composable
@CatGalleryPreview
fun GalleryItemPreview(
    item: CatInfo = CatInfo(
        url = "https://cdn2.thecatapi.com/images/ln.jpg",
        id = "ln",
        breeds = null
    )
) {
    Column {
        GalleryItem(item = item)
    }
}

