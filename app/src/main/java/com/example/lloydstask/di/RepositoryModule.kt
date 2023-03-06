package com.example.lloydstask.di

import com.example.lloydstask.data.implementation.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.data.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideMovieRemoteDataSource(
        apiService: ApiService
    ): MoviesRemoteDataSource = MoviesRemoteDataSource(apiService)
}