package com.example.lloydstask.di

import com.example.lloydstask.data.datasource.remotedatasource.MoviesRemoteDataSource
import com.example.lloydstask.data.repository.MoviesRepositoryImpl
import com.example.lloydstask.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesMovieRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource
    ): MoviesRepository = MoviesRepositoryImpl(moviesRemoteDataSource)
}