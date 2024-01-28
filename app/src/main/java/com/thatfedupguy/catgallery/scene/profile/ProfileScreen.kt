package com.thatfedupguy.catgallery.scene.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.thatfedupguy.catgallery.R
import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo
import com.thatfedupguy.catgallery.ui.theme.type.interRegular
import com.thatfedupguy.catgallery.utils.annotations.CatGalleryPreview
import com.thatfedupguy.catgallery.utils.composables.AppBar
import com.thatfedupguy.catgallery.utils.composables.AppImageView
import com.thatfedupguy.catgallery.utils.composables.ErrorText
import com.thatfedupguy.catgallery.utils.composables.Loader
import com.thatfedupguy.catgallery.utils.extns.orDefault

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.isLoading) {
        Loader()
    } else if (uiState.error != null) {
        ErrorText(text = uiState.error.orDefault())
    } else {
        Profile(
            catInfo = uiState.catInfo
        )
    }
}

@Composable
fun Profile(
    catInfo: CatInfo?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        AppBar(title = stringResource(R.string.cat_profile))
        AppImageView(
            modifier = Modifier
                .size(152.dp)
                .clip(CircleShape),
            model = catInfo?.url
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = stringResource(R.string.dummy_text),
            style = interRegular.titleMedium.copy(textAlign = TextAlign.Center)
        )
    }
}

@Composable
@CatGalleryPreview
fun ProfilePreview(
    catInfo: CatInfo = CatInfo(
        url = "https://cdn2.thecatapi.com/images/ln.jpg",
        id = "ln",
        breeds = null
    )
) {
    Profile(catInfo = catInfo)
}