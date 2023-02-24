package com.example.lloydstask.domain.usecases

import com.example.lloydstask.domain.repository.DogsRepository
import com.example.lloydstask.domain.model.DogsUrlModel
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDogUseCase @Inject constructor(
    private val dogsRepository: DogsRepository
) {
    suspend fun getDog() = dogsRepository.getDog()
}