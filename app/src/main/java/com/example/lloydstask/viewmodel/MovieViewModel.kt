package com.example.lloydstask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lloydstask.domain.model.MovieDetailsDomainModel
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.domain.usecases.MovieUseCase
import com.example.lloydstask.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _movieListState = MutableStateFlow<Result<MovieListDomainModel?>>(Result.Loading())
    val movieListState: StateFlow<Result<MovieListDomainModel?>> get() = _movieListState

    private val _movieDetailsState =
        MutableStateFlow<Result<MovieDetailsDomainModel?>>(Result.Loading())
    val movieDetailsState: StateFlow<Result<MovieDetailsDomainModel?>> get() = _movieDetailsState

    fun fetchMovieList() {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.getMovieList()
                .collect { result ->
                    _movieListState.value = result
                }
        }
    }

    fun fetchMovieDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.getMovieDetails(id)
                .collect { result ->
                    _movieDetailsState.value = result
                }
        }
    }
}