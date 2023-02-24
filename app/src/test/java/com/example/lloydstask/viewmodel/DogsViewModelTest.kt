package com.example.lloydstask.viewmodel

import com.example.lloydstask.BaseTest
import com.example.lloydstask.domain.model.DogsUrlModel
import com.example.lloydstask.domain.usecases.GetDogUseCase
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
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
class DogsViewModelTest : BaseTest() {
    private lateinit var dogsViewModel: DogsViewModel

    private val dogsUseCase: GetDogUseCase = mockk()
    private val dogsUrlModelResult: Flow<Result<DogsUrlModel>> = mockk()
    private val dogsUrlModel: DogsUrlModel = mockk()

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