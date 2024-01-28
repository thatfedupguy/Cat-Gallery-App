package com.thatfedupguy.catgallery.scene

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.thatfedupguy.catgallery.scene.gallery.GalleryDirections
import com.thatfedupguy.catgallery.scene.gallery.GalleryScreen
import com.thatfedupguy.catgallery.scene.profile.ProfileScreen

const val gallery = "gallery"
const val profile = "profile/{id}"

@Composable
fun CatGalleryNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = gallery
    ) {
        composable(gallery) {
            GalleryScreen(
                viewModel = hiltViewModel(),
                navigate = {
                    when (it) {
                        is GalleryDirections.CatProfile -> {
                            navHostController.navigate(route = "profile/${it.catInfo.id}")
                        }
                    }
                }
            )
        }
        composable(
            profile,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            ProfileScreen(
                viewModel = hiltViewModel()
            )
        }
    }
}