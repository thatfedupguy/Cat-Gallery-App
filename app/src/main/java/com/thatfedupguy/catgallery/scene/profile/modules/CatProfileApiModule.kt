package com.thatfedupguy.catgallery.scene.profile.modules

import com.thatfedupguy.catgallery.scene.profile.data.CatProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object CatProfileApiModule {

    @Provides
    fun provideCatProfileApi(retrofit: Retrofit): CatProfileApi =
        retrofit.create(CatProfileApi::class.java)
}