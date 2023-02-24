package com.example.lloydstask.data.services

import com.example.lloydstask.data.model.DogsResponse
import com.example.lloydstask.data.constants.Constants.Companion.RANDOM_URL
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(RANDOM_URL)
    suspend fun getDog(): Response<DogsResponse>
}