package com.example.lloydstask.di

import com.example.lloydstask.repository.DogsRepository
import com.example.lloydstask.usecases.GetDogUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideDogsUseCase(dogsRepository: DogsRepository): GetDogUseCase =
        GetDogUseCase(dogsRepository)
}