package com.example.animeapi.di

import com.example.core.domain.usecase.AnimeInteractor
import com.example.core.domain.usecase.AnimeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideAnimeUseCase(animeInteractor: AnimeInteractor): AnimeUseCase
}