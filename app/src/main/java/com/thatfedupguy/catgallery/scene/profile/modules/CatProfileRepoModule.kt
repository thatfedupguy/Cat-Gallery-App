package com.thatfedupguy.catgallery.scene.profile.modules

import com.thatfedupguy.catgallery.scene.profile.repo.CatProfileRepo
import com.thatfedupguy.catgallery.scene.profile.repo.CatProfileRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CatProfileRepoModule {
    @Binds
    abstract fun providesCatProfileRepo(catProfileRepoImpl: CatProfileRepoImpl): CatProfileRepo
}