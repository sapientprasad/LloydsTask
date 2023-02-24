package com.example.lloydstask.di

import com.example.lloydstask.domain.mapper.DogsModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object MapperModule {

    @Provides
    fun providesDogsModelMapper(): DogsModelMapper = DogsModelMapper()
}