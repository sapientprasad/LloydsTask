package com.example.lloydstask.domain.usecases

import com.example.lloydstask.BaseTest
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.domain.repository.MoviesRepository
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListUseCaseTest : BaseTest() {

    private val repository: MoviesRepository = mockk()
    private val movieListDomainModelResult: Flow<Result<MovieListDomainModel?>> = mockk()

    private lateinit var movieListUseCase: MovieListUseCase

    override fun setUp() {
        super.setUp()
        movieListUseCase = MovieListUseCase(repository)
    }

    @Test
    fun getMovieList_returnsMovieList() = runTest {
        coEvery { repository.getMovieList() } returns movieListDomainModelResult
        movieListUseCase()
        advanceUntilIdle()
        coVerify { repository.getMovieList() }
    }
}