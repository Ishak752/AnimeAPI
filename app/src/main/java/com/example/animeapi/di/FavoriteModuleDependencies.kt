package com.example.animeapi.di

import com.example.core.domain.usecase.AnimeUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun provideAnimeUseCase(): AnimeUseCase
}