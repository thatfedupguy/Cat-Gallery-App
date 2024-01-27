package com.thatfedupguy.catgallery.scene.modules

import com.thatfedupguy.catgallery.scene.api.CatApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object CatApiModule {

    @Provides
    fun providesCatApi(retrofit: Retrofit): CatApi = retrofit.create(CatApi::class.java)
}