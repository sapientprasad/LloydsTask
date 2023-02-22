package com.example.lloydstask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lloydstask.model.DogsUrlModel
import com.example.lloydstask.usecases.GetDogUseCase
import com.example.lloydstask.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val dogUseCase: GetDogUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MainActivityUiState>(MainActivityUiState.Init)
    val state: StateFlow<MainActivityUiState> get() = _state

    fun fetchDogsData() {
        viewModelScope.launch {
            dogUseCase.getDog()
                .catch {
                    _state.value = MainActivityUiState.Error(it.message)
                }
                .collect { result ->
                    when (result) {
                        is Result.Success ->
                            _state.value =
                                MainActivityUiState.Success(DogsUrlModel(result.data?.imageUrl))
                        is Result.Error ->
                            _state.value =
                                MainActivityUiState.Error(result.message)
                    }
                }
        }
    }
}

sealed interface MainActivityUiState {
    object Init : MainActivityUiState
    data class Success(val dogsUrlModel: DogsUrlModel) : MainActivityUiState
    data class Error(val errorMessage: String?) : MainActivityUiState
}