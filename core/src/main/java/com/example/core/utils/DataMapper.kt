package com.example.core.utils

import com.example.core.data.source.local.entity.AnimeEntity
import com.example.core.data.source.remote.response.DataResponse
import com.example.core.domain.model.Anime

object DataMapper {
    fun mapResponsesToEntities(input: List<DataResponse>): List<AnimeEntity> {
        val animeList = ArrayList<AnimeEntity>()
        input.map {
            val anime = AnimeEntity(
                id = it.id,
                synopsis = it.attributes.synopsis,
                description = it.attributes.description,
                title = it.attributes.title.title,
                averageRating = it.attributes.averageRating,
                startDate = it.attributes.startDate,
                endDate = it.attributes.endDate,
                ratingRank = it.attributes.ratingRank,
                status = it.attributes.status,
                posterImage = it.attributes.posterImage.posterImage,
                coverImage = it.attributes.coverImage.coverImage,
                isFavorite = false
            )
            animeList.add(anime)
        }
        return animeList
    }

    fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Anime> =
        input.map {
            Anime(
                id = it.id,
                synopsis = it.synopsis,
                description = it.description,
                title = it.title,
                averageRating = it.averageRating,
                startDate = it.startDate,
                endDate = it.endDate,
                ratingRank = it.ratingRank,
                status = it.status,
                coverImage = it.coverImage,
                posterImage = it.posterImage,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Anime) = AnimeEntity(
        id = input.id,
        synopsis = input.synopsis,
        description = input.description,
        title = input.title,
        averageRating = input.averageRating,
        startDate = input.startDate,
        endDate = input.endDate,
        ratingRank = input.ratingRank,
        status = input.status,
        coverImage = input.coverImage,
        posterImage = input.posterImage,
        isFavorite = input.isFavorite
    )
}