package com.example.lloydstask.domain.usecases

import com.example.lloydstask.data.implementation.datasource.remotedatasource.MoviesRemoteDataSource
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource
) {
    suspend fun getMovieList() = remoteDataSource.getMovieList()

    suspend fun getMovieDetails(id: String) = remoteDataSource.getMovieDetails(id)
}