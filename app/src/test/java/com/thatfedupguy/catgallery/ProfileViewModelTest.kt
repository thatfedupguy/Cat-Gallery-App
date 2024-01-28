package com.thatfedupguy.catgallery

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.thatfedupguy.catgallery.scene.gallery.data.CatInfo
import com.thatfedupguy.catgallery.scene.profile.ProfileViewModel
import com.thatfedupguy.catgallery.scene.profile.repo.CatProfileRepo
import com.thatfedupguy.catgallery.utils.ApiResult
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ProfileViewModelTest {

    @Mock
    lateinit var repo: CatProfileRepo
    private lateinit var ioDispatcher: CoroutineDispatcher
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var emptySavedStateHandle: SavedStateHandle

    @Before
    fun setup() {
        ioDispatcher = UnconfinedTestDispatcher()
        savedStateHandle = SavedStateHandle(mapOf(Pair("id", "ln")))
        emptySavedStateHandle = SavedStateHandle()
    }

    private val dummyProfile = CatInfo(
        url = "https://cdn2.thecatapi.com/images/ln.jpg",
        id = "ln",
        breeds = null
    )

    @Test
    fun `success for fetching the cat profile by id`() {
        runTest {
            doReturn(ApiResult.Success(dummyProfile)).`when`(repo).getCat("ln")
            val viewModel = ProfileViewModel(savedStateHandle, repo, ioDispatcher)
            viewModel.uiState.test {
                assertEquals(dummyProfile, awaitItem().catInfo)
                cancelAndIgnoreRemainingEvents()
            }
            verify(repo).getCat("ln")
        }
    }

    @Test
    fun `throws exception in case of server exception`() {
        runTest {
            val errorMessage = "Something went wrong"
            doReturn(ApiResult.Error(message = errorMessage)).`when`(repo).getCat("ln")
            val viewModel = ProfileViewModel(savedStateHandle, repo, ioDispatcher)
            viewModel.uiState.test {
                assertEquals(errorMessage, awaitItem().error)
                cancelAndIgnoreRemainingEvents()
            }
            verify(repo).getCat("ln")
        }
    }

    @Test
    fun `if username is empty string then show error`() {
        runTest {
            val viewModel = ProfileViewModel(emptySavedStateHandle, repo, ioDispatcher)
            viewModel.uiState.test {
                assertEquals("Something went wrong", awaitItem().error)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}