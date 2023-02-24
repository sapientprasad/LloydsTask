package com.example.lloydstask.domain.repository

import com.example.lloydstask.BaseTest
import com.example.lloydstask.data.implementation.DogsRepoImpl
import com.example.lloydstask.data.services.ApiService
import com.example.lloydstask.data.model.DogsResponse
import com.example.lloydstask.domain.model.DogsUrlModel
import com.example.lloydstask.domain.mapper.DogsModelMapper
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class DogsRepoImplTest : BaseTest() {

    private val apiService: ApiService = mockk()
    private val dogsModelMapper: DogsModelMapper = mockk()
    private val dogsResponseMock: Response<DogsResponse> = mockk()
    private val dogsResponse: DogsResponse = mockk()
    private val dogsUrlModel: DogsUrlModel = mockk()

    private lateinit var dogsRepoImpl: DogsRepoImpl

    @Before
    override fun setUp() {
        super.setUp()
        dogsRepoImpl = DogsRepoImpl(apiService, dogsModelMapper)
    }

    @Test
    fun getDog_returnsSuccess() = runTest {
        every { dogsModelMapper.toDogsUrlModel(dogsResponse) } returns dogsUrlModel
        every { dogsResponseMock.body() } returns dogsResponse
        every { dogsResponseMock.isSuccessful } returns true
        coEvery { apiService.getDog() } returns dogsResponseMock
        val dogDetails = dogsRepoImpl.getDog()
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
        val dogDetails = dogsRepoImpl.getDog()
        advanceUntilIdle()
        var result: Result<DogsUrlModel>? = null
        dogDetails.collect {
            result = it
        }
        assert(result is Result.Error)
    }
}