package com.pixabayfreephotos.data

import com.pixabayfreephotos.data.model.PhotoData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val api: APIService) : DataSource {
    override suspend fun getPhoto(currentPage: Int): Result<PhotoData> {
        return kotlin.runCatching { api.getApiResponse(currentPage) }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceAbstract {
    @Binds
    abstract fun dataSourceAbstract(dataSourceImpl: DataSourceImpl): DataSource
}
