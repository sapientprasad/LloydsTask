package com.example.lloydstask.usecases

import com.example.lloydstask.BaseTest
import com.example.lloydstask.model.DogsUrlModel
import com.example.lloydstask.repository.DogsRepository
import com.example.lloydstask.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetDogUseCaseTest : BaseTest() {

    @MockK
    private lateinit var dogsRepository: DogsRepository

    @MockK
    private lateinit var dogsUrlModelResult: Flow<Result<DogsUrlModel>>

    @Test
    fun getDog_returnsDogData() = runTest {
        coEvery { dogsRepository.getDog() } returns dogsUrlModelResult
        val dogsUseCase = GetDogUseCase(dogsRepository)
        dogsUseCase.getDog()
        advanceUntilIdle()
        coVerify { dogsRepository.getDog() }
    }
}