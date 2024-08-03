package com.pixabayfreephotos.data

import com.pixabayfreephotos.data.model.Hit
import com.pixabayfreephotos.data.model.PhotoData
import com.pixabayfreephotos.domain.Repository
import com.pixabayfreephotos.domain.model.Hits
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val dataSource: DataSource) : Repository {
    override suspend fun getPhoto(currentPage: Int): Result<PhotoData> {
        return dataSource.getPhoto(currentPage)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryAbstract() {
    @Binds
    abstract fun repositoryAbstract(repositoryImpl: RepositoryImpl): Repository
}

fun Hit.toDomain() = Hits(
    collections = collections,
    comments = comments,
    downloads = downloads,
    id = id,
    imageHeight = imageHeight,
    imageSize = imageSize,
    imageWidth = imageWidth,
    largeImageURL = largeImageURL,
    likes = likes,
    pageURL = pageURL,
    previewHeight = previewHeight,
    previewURL = previewURL,
    previewWidth = previewWidth,
    tags = tags,
    type = type,
    user = user,
    userImageURL = userImageURL,
    user_id = user_id,
    views = views,
    webformatHeight = webformatHeight,
    webformatURL = webformatURL,
    webformatWidth = webformatWidth
)