package com.thatfedupguy.catgallery.scene.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo
import com.thatfedupguy.catgallery.scene.profile.repo.CatProfileRepo
import com.thatfedupguy.catgallery.utils.ApiResult
import com.thatfedupguy.catgallery.utils.annotations.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: CatProfileRepo,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val id = savedStateHandle["id"] ?: ""
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    init {
        if (id.isNotEmpty()) {
            getCatInfo()
        } else {
            _uiState.update {
                it.copy(
                    error = "Something went wrong"
                )
            }
        }
    }

    private fun getCatInfo() {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(ioDispatcher) {
            when (val response = repo.getCat(id)) {
                is ApiResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }

                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            catInfo = response.data
                        )
                    }
                }
            }
        }
    }

}

data class ProfileUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val catInfo: CatInfo? = null
)