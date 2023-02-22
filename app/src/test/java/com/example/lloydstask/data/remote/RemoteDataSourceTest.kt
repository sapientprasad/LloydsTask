package com.example.lloydstask.data.remote

import com.example.lloydstask.BaseTest
import com.example.lloydstask.api.ApiService
import com.example.lloydstask.mapper.DogsModelMapper
import com.example.lloydstask.model.DogsResponse
import com.example.lloydstask.model.DogsUrlModel
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RemoteDataSourceTest : BaseTest() {

    @MockK
    private lateinit var apiService: ApiService

    @MockK
    private lateinit var dogsModelMapper: DogsModelMapper

    @MockK
    private lateinit var dogsResponseMock: Response<DogsResponse>

    @MockK
    private lateinit var dogsResponse: DogsResponse

    @MockK
    private lateinit var dogsUrlModel: DogsUrlModel

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    override fun setUp() {
        super.setUp()
        remoteDataSource = RemoteDataSource(apiService, dogsModelMapper)
    }

    @Test
    fun getDog_returnsSuccess() = runTest {
        every { dogsModelMapper.toDogsUrlModel(dogsResponse) } returns dogsUrlModel
        every { dogsResponseMock.body() } returns dogsResponse
        every { dogsResponseMock.isSuccessful } returns true
        coEvery { apiService.getDog() } returns dogsResponseMock
        val dogDetails = remoteDataSource.getDog()
        advanceUntilIdle()
        var result: Result<DogsUrlModel>? = null
        dogDetails.collect {
            result = it
        }
        assert(result is Result.Success)
    }

    @Test
    fun getDog_returnsError() = runTest {
        every { dogsResponseMock.isSuccessful } returns false
        coEvery { apiService.getDog() } returns dogsResponseMock
        val dogDetails = remoteDataSource.getDog()
        advanceUntilIdle()
        var result: Result<DogsUrlModel>? = null
        dogDetails.collect {
            result = it
        }
        assert(result is Result.Error)
    }
}