package com.example.lloydstask.data.implementation

import com.example.lloydstask.data.services.ApiService
import com.example.lloydstask.domain.mapper.DogsModelMapper
import com.example.lloydstask.data.model.BaseApiResponse
import com.example.lloydstask.domain.model.DogsUrlModel
import com.example.lloydstask.domain.repository.DogsRepository
import com.example.lloydstask.data.constants.Constants.Companion.EMPTY_STRING
import com.example.lloydstask.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class DogsRepoImpl @Inject constructor(
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