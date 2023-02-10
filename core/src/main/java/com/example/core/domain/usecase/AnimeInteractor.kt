package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Anime
import com.example.core.domain.repository.IAnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeInteractor @Inject constructor(private val animeRepository: IAnimeRepository) :
    AnimeUseCase {
    override fun getAllAnime(): Flow<Resource<List<Anime>>> = animeRepository.getAllAnime()

    override fun getFavoriteAnime(): Flow<List<Anime>> = animeRepository.getFavoriteAnime()

    override fun setFavoriteAnime(anime: Anime, state: Boolean) =
        animeRepository.setFavoriteAnime(anime, state)

}