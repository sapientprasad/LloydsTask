package com.example.lloydstask.data.remote

import com.example.lloydstask.api.ApiService
import com.example.lloydstask.mapper.DogsModelMapper
import com.example.lloydstask.model.BaseApiResponse
import com.example.lloydstask.model.DogsUrlModel
import com.example.lloydstask.repository.DogsRepository
import com.example.lloydstask.utils.Constants.Companion.EMPTY_STRING
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val dogsModelMapper: DogsModelMapper
) : DogsRepository, BaseApiResponse() {
    override suspend fun getDog(): Flow<Result<DogsUrlModel>> {
        return flow {
            emit(safeApiCall { mapResponseToModel() })
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun mapResponseToModel(): Response<DogsUrlModel> {
        val response = apiService.getDog()
        return if (response.isSuccessful) {
            Response.success(dogsModelMapper.toDogsUrlModel(response.body()))
        } else {
            Response.error(response.code(), response.errorBody() ?: EMPTY_STRING.toResponseBody())
        }
    }
}