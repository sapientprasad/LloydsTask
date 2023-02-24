package com.example.lloydstask.domain.mapper

import com.example.lloydstask.data.model.DogsResponse
import com.example.lloydstask.domain.model.DogsUrlModel
import javax.inject.Inject

class DogsModelMapper @Inject constructor() {
    fun toDogsUrlModel(dogsResponse: DogsResponse?) = DogsUrlModel(dogsResponse?.message)
}