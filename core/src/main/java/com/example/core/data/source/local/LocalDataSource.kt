package com.example.core.data.source.local

import com.example.core.data.source.local.entity.AnimeEntity
import com.example.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val animeDao: AnimeDao) {
    fun getAllAnime(): Flow<List<AnimeEntity>> = animeDao.getAllAnime()

    fun getFavoriteAnime(): Flow<List<AnimeEntity>> = animeDao.getFavoriteAnime()

    suspend fun insertAnime(animeList: List<AnimeEntity>) = animeDao.insertAnime(animeList)

    fun setFavoriteAnime(anime: AnimeEntity, newState: Boolean) {
        anime.isFavorite = newState
        animeDao.updateFavoriteAnime(anime)
    }
}