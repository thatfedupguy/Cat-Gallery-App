package com.thatfedupguy.catgallery.scene.gallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.thatfedupguy.catgallery.R
import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo
import com.thatfedupguy.catgallery.ui.theme.type.interSemiBold
import com.thatfedupguy.catgallery.utils.annotations.CatGalleryPreview
import com.thatfedupguy.catgallery.utils.composables.AppBar
import com.thatfedupguy.catgallery.utils.composables.AppImageView
import com.thatfedupguy.catgallery.utils.composables.PagingLoadingComposable
import com.thatfedupguy.catgallery.utils.composables.appClickable
import com.thatfedupguy.catgallery.utils.extns.orDefault
import kotlinx.coroutines.flow.Flow

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel,
    navigate: (GalleryDirections) -> Unit
) {
    Gallery(
        galleryItems = viewModel.cats,
        navigate = navigate
    )
}

@Composable
fun Gallery(
    galleryItems: Flow<PagingData<CatInfo>>?,
    navigate: (GalleryDirections) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppBar(title = stringResource(R.string.cat_gallery))
        Box(modifier = Modifier.fillMaxSize()) {
            val catInfos = galleryItems?.collectAsLazyPagingItems()
            val itemCount = catInfos?.itemCount ?: 0
            val loadState = catInfos?.loadState
            if (itemCount == 0 && loadState?.refresh is LoadState.Loading) {
                GalleryShimmer()
            } else {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (loadState?.append is LoadState.NotLoading && loadState.append.endOfPaginationReached) {
                        Text(
                            stringResource(R.string.no_cats_found),
                            style = interSemiBold.titleMedium
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            state = rememberLazyListState(),
                            content = {
                                items(count = itemCount) { index ->
                                    val catInfo = catInfos?.get(index)
                                    catInfo?.let {
                                        GalleryItem(
                                            item = it,
                                            onClick = {
                                                navigate(GalleryDirections.CatProfile(catInfo))
                                            }
                                        )
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
        }
    }
}

@Composable
fun GalleryItem(
    modifier: Modifier = Modifier,
    item: CatInfo,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(corner = CornerSize(8.dp))
            )
            .padding(16.dp)
            .appClickable {
                onClick()
            },
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AppImageView(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = item.url
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(R.string.id, item.id.orDefault()),
                style = interSemiBold.titleMedium
            )
            Text(
                text = stringResource(R.string.breed, item.getBreed()),
                style = interSemiBold.titleMedium
            )
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

