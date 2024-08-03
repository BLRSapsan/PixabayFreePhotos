package com.pixabayfreephotos.data.model

import com.google.gson.annotations.SerializedName

data class PhotoData(
    @SerializedName("hits") val hits: List<Hit>,
    @SerializedName("total")val total: Int,
    @SerializedName("totalHits")val totalHits: Int
)