package com.example.lloydstask.di

import android.content.Context
import com.example.lloydstask.wrapper.GlideImageLoader
import com.example.lloydstask.wrapper.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageLoaderModule {
    @Singleton
    @Provides
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader =
        GlideImageLoader(context)
}