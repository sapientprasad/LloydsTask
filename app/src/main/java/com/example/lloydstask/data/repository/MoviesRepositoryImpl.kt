package com.example.lloydstask.data.repository

import com.example.lloydstask.data.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.domain.repository.MoviesRepository
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {
    override suspend fun getMovieList(): Flow<Result<MovieListDomainModel?>> {
        return remoteDataSource.getMovieList()
    }

    override suspend fun getMovieDetails(id: String): Flow<Result<MovieDetailsDomainModel?>> {
        return remoteDataSource.getMovieDetails(id)
    }
}