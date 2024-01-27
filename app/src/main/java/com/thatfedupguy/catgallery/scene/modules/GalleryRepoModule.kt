package com.thatfedupguy.catgallery.scene.modules

import com.thatfedupguy.catgallery.scene.repo.GalleryRepo
import com.thatfedupguy.catgallery.scene.repo.repoImpl.GalleryRepoImpl
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