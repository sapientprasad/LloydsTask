package com.example.lloydstask.repository

import com.example.lloydstask.model.DogsUrlModel
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    suspend fun getDog(): Flow<Result<DogsUrlModel>>
}