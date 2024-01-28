package com.thatfedupguy.catgallery.scene.gallery.modules

import com.thatfedupguy.catgallery.scene.gallery.repo.GalleryRepo
import com.thatfedupguy.catgallery.scene.gallery.repo.repoImpl.GalleryRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GalleryRepoModule {

    @Binds
    abstract fun provideGalleryRepo(galleryRepoImpl: GalleryRepoImpl): GalleryRepo
}