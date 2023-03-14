package com.example.lloydstask.domain.usecases

import com.example.lloydstask.data.repository.MoviesRepository
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(): Flow<Result<MovieListDomainModel?>> {
        return moviesRepository.getMovieList()
    }
}