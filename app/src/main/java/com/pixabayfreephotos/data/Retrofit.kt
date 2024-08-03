package com.pixabayfreephotos.data

import com.pixabayfreephotos.data.model.PhotoData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

const val BASE_URL = "https://pixabay.com/api/"
const val API_KEY = "?key=YOUR KEY"

interface APIService {
    @GET(API_KEY)
    suspend fun getApiResponse(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30,
        @Query("image_type") imageType: String = "photo"
    ): PhotoData
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)
}
