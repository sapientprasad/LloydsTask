package com.example.lloydstask.api

import com.example.lloydstask.model.DogsResponse
import com.example.lloydstask.utils.Constants.Companion.RANDOM_URL
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(RANDOM_URL)
    suspend fun getDog(): Response<DogsResponse>
}