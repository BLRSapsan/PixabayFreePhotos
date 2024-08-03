package com.pixabayfreephotos.data

import com.pixabayfreephotos.data.model.PhotoData

interface DataSource {
    suspend fun getPhoto(currentPage: Int):Result<PhotoData>
}