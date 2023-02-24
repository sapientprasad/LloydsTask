package com.example.lloydstask.viewmodel

import com.example.lloydstask.domain.model.DogsUrlModel

sealed interface MainActivityUiState {
    object Init : MainActivityUiState
    data class Success(val dogsUrlModel: DogsUrlModel) : MainActivityUiState
    data class Error(val errorMessage: String?) : MainActivityUiState
}