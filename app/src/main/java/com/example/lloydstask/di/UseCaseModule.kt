package com.example.lloydstask.di

import com.example.lloydstask.domain.repository.DogsRepository
import com.example.lloydstask.domain.usecases.GetDogUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideDogsUseCase(dogsRepository: DogsRepository): GetDogUseCase =
        GetDogUseCase(dogsRepository)
}