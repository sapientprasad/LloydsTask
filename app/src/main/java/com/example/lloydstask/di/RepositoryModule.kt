package com.example.lloydstask.di

import com.example.lloydstask.api.ApiService
import com.example.lloydstask.data.remote.RemoteDataSource
import com.example.lloydstask.mapper.DogsModelMapper
import com.example.lloydstask.repository.DogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideDogsRepository(
        apiService: ApiService,
        dogsModelMapper: DogsModelMapper
    ): DogsRepository =
        RemoteDataSource(apiService, dogsModelMapper)
}