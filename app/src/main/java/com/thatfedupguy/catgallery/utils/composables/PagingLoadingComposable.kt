package com.thatfedupguy.catgallery.utils.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.thatfedupguy.catgallery.ui.theme.type.interSemiBold

@Composable
fun PagingLoadingComposable(loadState: CombinedLoadStates?) {
    when {
        loadState?.append is LoadState.Loading -> {
            Loader()
        }

        loadState?.refresh is LoadState.NotLoading -> {
            Loader()
        }

        loadState?.append is LoadState.Error || loadState?.refresh is LoadState.Error -> {
            Text(
                text = "Uh-Oh Something went wrong!",
                style = interSemiBold.titleMedium
            )
        }

    }
}