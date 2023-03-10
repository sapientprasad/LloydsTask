package com.example.lloydstask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lloydstask.di.IoDispatcher
import com.example.lloydstask.domain.model.MovieListDomainModel
import com.example.lloydstask.domain.usecases.MovieListUseCase
import com.example.lloydstask.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _movieListState = MutableStateFlow<Result<MovieListDomainModel?>>(Result.Loading())
    val movieListState: StateFlow<Result<MovieListDomainModel?>> get() = _movieListState

    fun fetchMovieList() {
        viewModelScope.launch(ioDispatcher) {
            movieListUseCase()
                .collect { result ->
                    _movieListState.value = result
                }
        }
    }
}