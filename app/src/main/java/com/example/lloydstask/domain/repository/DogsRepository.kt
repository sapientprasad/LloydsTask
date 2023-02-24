package com.example.lloydstask.domain.repository

import com.example.lloydstask.domain.model.DogsUrlModel
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    suspend fun getDog(): Flow<Result<DogsUrlModel>>
}