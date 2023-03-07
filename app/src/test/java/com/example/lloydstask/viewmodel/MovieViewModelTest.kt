package com.example.lloydstask.viewmodel

import com.example.lloydstask.BaseTest
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.domain.usecases.MovieUseCase
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest : BaseTest() {
    private lateinit var movieViewModel: MovieViewModel

    private val movieUseCase: MovieUseCase = mockk()
    private val movieListDomainModelResult: Flow<Result<MovieListDomainModel?>> = mockk()
    private val movieDetailsDomainModelResult: Flow<Result<MovieDetailsDomainModel?>> = mockk()
    private val movieListDomainModel: MovieListDomainModel = mockk()
    private val movieDetailsDomainModel: MovieDetailsDomainModel = mockk()

    @Before
    override fun setUp() {
        super.setUp()
        movieViewModel = MovieViewModel(movieUseCase, testDispatcher)
    }

    @Test
    fun fetchMovieList_returnsSuccess() = runTest {
        coEvery { movieUseCase.getMovieList() } returns movieListDomainModelResult
        coEvery { movieUseCase.getMovieList() } returns flow {
            emit(
                Result.Success(
                    movieListDomainModel
                )
            )
        }
        movieViewModel.fetchMovieList()
        advanceUntilIdle()
        coVerify { movieUseCase.getMovieList() }
        assertTrue(movieViewModel.movieListState.value is Result.Success)
    }

    @Test
    fun fetchMovieList_returnsError() = runTest {
        coEvery { movieUseCase.getMovieList() } returns movieListDomainModelResult
        coEvery { movieUseCase.getMovieList() } returns flow {
            emit(
                Result.Error(
                    DEFAULT_ERROR_MESSAGE
                )
            )
        }
        movieViewModel.fetchMovieList()
        advanceUntilIdle()
        coVerify { movieUseCase.getMovieList() }
        assertTrue(movieViewModel.movieListState.value.message == DEFAULT_ERROR_MESSAGE)
    }

    @Test
    fun fetchMovieDetails_returnsSuccess() = runTest {
        coEvery { movieUseCase.getMovieDetails(MOVIE_ID) } returns movieDetailsDomainModelResult
        coEvery { movieUseCase.getMovieDetails(MOVIE_ID) } returns flow {
            emit(
                Result.Success(
                    movieDetailsDomainModel
                )
            )
        }
        movieViewModel.fetchMovieDetails(MOVIE_ID)
        advanceUntilIdle()
        coVerify { movieUseCase.getMovieDetails(MOVIE_ID) }
        assertTrue(movieViewModel.movieDetailsState.value is Result.Success)
    }

    @Test
    fun fetchMovieDetails_returnsError() = runTest {
        coEvery { movieUseCase.getMovieDetails(MOVIE_ID) } returns movieDetailsDomainModelResult
        coEvery { movieUseCase.getMovieDetails(MOVIE_ID) } returns flow {
            emit(
                Result.Error(
                    DEFAULT_ERROR_MESSAGE
                )
            )
        }
        movieViewModel.fetchMovieDetails(MOVIE_ID)
        advanceUntilIdle()
        coVerify { movieUseCase.getMovieDetails(MOVIE_ID) }
        assertTrue(movieViewModel.movieDetailsState.value.message == DEFAULT_ERROR_MESSAGE)
    }
}