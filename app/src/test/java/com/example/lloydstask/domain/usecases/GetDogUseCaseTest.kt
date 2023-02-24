package com.example.lloydstask.domain.usecases

import com.example.lloydstask.BaseTest
import com.example.lloydstask.domain.model.DogsUrlModel
import com.example.lloydstask.domain.repository.DogsRepository
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
class GetDogUseCaseTest : BaseTest() {

    private val dogsRepository: DogsRepository = mockk()
    private val dogsUrlModelResult: Flow<Result<DogsUrlModel>> = mockk()

    @Test
    fun getDog_returnsDogData() = runTest {
        coEvery { dogsRepository.getDog() } returns dogsUrlModelResult
        val dogsUseCase = GetDogUseCase(dogsRepository)
        dogsUseCase.getDog()
        advanceUntilIdle()
        coVerify { dogsRepository.getDog() }
    }
}