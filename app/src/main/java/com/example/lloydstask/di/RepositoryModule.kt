package com.example.lloydstask.di

import com.example.lloydstask.data.services.ApiService
import com.example.lloydstask.data.implementation.DogsRepoImpl
import com.example.lloydstask.domain.mapper.DogsModelMapper
import com.example.lloydstask.domain.repository.DogsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideDogsRepository(
        apiService: ApiService,
        dogsModelMapper: DogsModelMapper
    ): DogsRepository =
        DogsRepoImpl(apiService, dogsModelMapper)
}