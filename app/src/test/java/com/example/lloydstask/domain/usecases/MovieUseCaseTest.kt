package com.example.lloydstask.domain.usecases

import com.example.lloydstask.BaseTest
import com.example.lloydstask.data.implementation.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.domain.model.MovieListDomainModel
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
class MovieUseCaseTest : BaseTest() {

    private val remoteDataSource: MoviesRemoteDataSource = mockk()
    private val movieListDomainModelResult: Flow<Result<MovieListDomainModel?>> = mockk()
    private val movieDetailsDomainModelResult: Flow<Result<MovieDetailsDomainModel?>> = mockk()

    private lateinit var movieUseCase: MovieUseCase

    override fun setUp() {
        super.setUp()
        movieUseCase = MovieUseCase(remoteDataSource)
    }

    @Test
    fun getMovieList_returnsMovieList() = runTest {
        coEvery { remoteDataSource.getMovieList() } returns movieListDomainModelResult
        movieUseCase.getMovieList()
        advanceUntilIdle()
        coVerify { remoteDataSource.getMovieList() }
    }

    @Test
    fun getMovieDetails_returnsMovieList() = runTest {
        coEvery { remoteDataSource.getMovieDetails(MOVIE_ID) } returns movieDetailsDomainModelResult
        movieUseCase.getMovieDetails(MOVIE_ID)
        advanceUntilIdle()
        coVerify { remoteDataSource.getMovieDetails(MOVIE_ID) }
    }
}