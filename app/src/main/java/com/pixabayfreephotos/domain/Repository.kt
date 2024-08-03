package com.pixabayfreephotos.domain

import com.pixabayfreephotos.data.model.PhotoData

interface Repository {
    suspend fun getPhoto(currentPage: Int): Result<PhotoData>
}