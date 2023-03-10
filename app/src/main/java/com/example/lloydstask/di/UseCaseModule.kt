package com.example.lloydstask.di

import com.example.lloydstask.domain.repository.MoviesRepository
import com.example.lloydstask.domain.usecases.MovieDetailsUseCase
import com.example.lloydstask.domain.usecases.MovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesMovieListUseCase(
        repository: MoviesRepository
    ): MovieListUseCase = MovieListUseCase(repository)

    @Provides
    fun providesMovieDetailsUseCase(
        repository: MoviesRepository
    ): MovieDetailsUseCase = MovieDetailsUseCase(repository)
}