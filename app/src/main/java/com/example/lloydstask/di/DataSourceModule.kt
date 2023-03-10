package com.example.lloydstask.di

import com.example.lloydstask.data.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.data.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object DataSourceModule {

    @Provides
    fun provideMovieRemoteDataSource(
        apiService: ApiService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): MoviesRemoteDataSource = MoviesRemoteDataSource(apiService, ioDispatcher)
}