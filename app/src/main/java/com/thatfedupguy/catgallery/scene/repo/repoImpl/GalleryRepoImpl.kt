package com.thatfedupguy.catgallery.scene.repo.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thatfedupguy.catgallery.scene.api.CatApi
import com.thatfedupguy.catgallery.scene.data.CatInfo
import com.thatfedupguy.catgallery.scene.repo.GalleryRepo
import com.thatfedupguy.catgallery.utils.CommonPagingSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class GalleryRepoImpl @Inject constructor(
    private val catApi: CatApi
) : GalleryRepo {

    override fun getCats(): Flow<PagingData<CatInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                enablePlaceholders = true,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                CommonPagingSource { pageCount, pageSize, _ ->
                    catApi.getCats(pageCount, pageSize).body()?.toList().orEmpty()
                }
            }
        ).flow
    }
}