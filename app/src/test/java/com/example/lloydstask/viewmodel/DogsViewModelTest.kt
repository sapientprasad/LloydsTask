package com.example.lloydstask.viewmodel

import com.example.lloydstask.BaseTest
import com.example.lloydstask.model.DogsUrlModel
import com.example.lloydstask.usecases.GetDogUseCase
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DogsViewModelTest : BaseTest() {

    @MockK
    private lateinit var dogsUseCase: GetDogUseCase

    @MockK
    private lateinit var dogsUrlModelResult: Flow<Result<DogsUrlModel>>

    @MockK
    private lateinit var dogsUrlModel: DogsUrlModel

    private lateinit var dogsViewModel: DogsViewModel

    @Before
    override fun setUp() {
        super.setUp()
        dogsViewModel = DogsViewModel(dogsUseCase)
    }

    @Test
    fun fetchDogsData_init_call() = runTest {
        coEvery { dogsUseCase.getDog() } returns dogsUrlModelResult
        dogsViewModel.fetchDogsData()
        advanceUntilIdle()
        assertTrue(dogsViewModel.state.value == MainActivityUiState.Init)
        coVerify { dogsUseCase.getDog() }
    }

    @Test
    fun fetchDogsData_returnsSuccess() = runTest {
        every { dogsUrlModel.imageUrl } returns "url"
        coEvery { dogsUseCase.getDog() } returns flow { emit(Result.Success(dogsUrlModel)) }
        dogsViewModel.fetchDogsData()
        advanceUntilIdle()
        assertTrue(dogsViewModel.state.value == MainActivityUiState.Success(dogsUrlModel))
    }

    @Test
    fun fetchDogsData_returnsError() = runTest {
        coEvery { dogsUseCase.getDog() } returns flow { emit(Result.Error("Error")) }
        dogsViewModel.fetchDogsData()
        advanceUntilIdle()
        assertTrue(dogsViewModel.state.value == MainActivityUiState.Error("Error"))
    }

    @Test
    fun fetchDogsData_onException() = runTest {
        val expected = Exception("Something went wrong")
        coEvery { dogsUseCase.getDog() } returns flow { throw expected }
        dogsViewModel.fetchDogsData()
        advanceUntilIdle()
        assertTrue(dogsViewModel.state.value == MainActivityUiState.Error("Something went wrong"))
    }
}