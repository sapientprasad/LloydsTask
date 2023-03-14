package com.example.lloydstask.domain.usecases

import com.example.lloydstask.data.repository.MoviesRepository
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(id: String): Flow<Result<MovieDetailsDomainModel?>> {
        return moviesRepository.getMovieDetails(id)
    }
}