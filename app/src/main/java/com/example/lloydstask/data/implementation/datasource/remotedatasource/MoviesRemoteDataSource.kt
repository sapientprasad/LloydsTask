package com.example.lloydstask.data.implementation.datasource.remotedatasource

import com.example.lloydstask.BuildConfig
import com.example.lloydstask.data.services.ApiService
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.domain.repository.MoviesRepository
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : MoviesRepository {
    override suspend fun getMovieList(): Flow<Result<MovieListDomainModel?>> {
        return flow {
            val response = apiService.getMoviesList(Locale.US.language, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.toMovieListDomainModel()))
            } else {
                emit(Result.Error(response.message()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieDetails(id: String): Flow<Result<MovieDetailsDomainModel?>> {
        return flow {
            val response = apiService.getMovieDetails(Locale.US.language, BuildConfig.API_KEY, id)
            if (response.isSuccessful) {
                emit(Result.Success(response.body()?.toMovieDetailsDomainModel()))
            } else {
                emit(Result.Error(response.message()))
            }
        }.flowOn(Dispatchers.IO)
    }
}