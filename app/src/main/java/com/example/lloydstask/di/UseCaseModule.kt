package com.example.lloydstask.di

import com.example.lloydstask.data.implementation.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.domain.usecases.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideMovieUseCase(
        remoteDataSource: MoviesRemoteDataSource
    ): MovieUseCase = MovieUseCase(remoteDataSource)
}