package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeUseCase {
    fun getAllAnime(): Flow<Resource<List<Anime>>>
    fun getFavoriteAnime(): Flow<List<Anime>>
    fun setFavoriteAnime(anime: Anime, state: Boolean)
}