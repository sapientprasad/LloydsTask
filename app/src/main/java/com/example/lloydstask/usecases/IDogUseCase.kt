package com.example.lloydstask.usecases

import com.example.lloydstask.model.DogsUrlModel
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow

interface IDogUseCase {
    suspend fun getDog(): Flow<Result<DogsUrlModel>>
}