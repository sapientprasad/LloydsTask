package com.example.lloydstask.repository

import com.example.lloydstask.BaseTest
import com.example.lloydstask.data.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.data.repository.MoviesRepositoryImpl
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
class MoviesRepositoryImplTest: BaseTest() {

    private val remoteDataSource: MoviesRemoteDataSource = mockk()
    private val movieListDomainModelResult: Flow<Result<MovieListDomainModel?>> = mockk()
    private val movieDetailsDomainModelResult: Flow<Result<MovieDetailsDomainModel?>> = mockk()

    private lateinit var moviesRepositoryImpl: MoviesRepositoryImpl

    override fun setUp() {
        super.setUp()
        moviesRepositoryImpl = MoviesRepositoryImpl(remoteDataSource)
    }

    @Test
    fun getMovieList_returnMovieList() = runTest {
        coEvery { remoteDataSource.getMovieList() } returns movieListDomainModelResult

        moviesRepositoryImpl.getMovieList()
        advanceUntilIdle()

        coVerify { remoteDataSource.getMovieList() }
    }

    @Test
    fun getMovieDetails_returnMovieDetails() = runTest {
        coEvery { remoteDataSource.getMovieDetails(MOVIE_ID) } returns movieDetailsDomainModelResult

        moviesRepositoryImpl.getMovieDetails(MOVIE_ID)
        advanceUntilIdle()

        coVerify { remoteDataSource.getMovieDetails(MOVIE_ID) }
    }
}