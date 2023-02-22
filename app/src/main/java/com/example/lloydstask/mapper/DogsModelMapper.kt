package com.example.lloydstask.mapper

import com.example.lloydstask.model.DogsResponse
import com.example.lloydstask.model.DogsUrlModel
import javax.inject.Inject

class DogsModelMapper @Inject constructor() {
    fun toDogsUrlModel(dogsResponse: DogsResponse?): DogsUrlModel {
        return DogsUrlModel(dogsResponse?.message)
    }
}