package com.example.lloydstask.usecases

import com.example.lloydstask.model.DogsUrlModel
import com.example.lloydstask.repository.DogsRepository
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDogUseCase @Inject constructor(
    private val dogsRepository: DogsRepository
) : IDogUseCase {
    override suspend fun getDog(): Flow<Result<DogsUrlModel>> {
        return dogsRepository.getDog()
    }
}