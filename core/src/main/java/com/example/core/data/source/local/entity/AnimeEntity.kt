package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "synopsis")
    var synopsis: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "titles")
    var title: String?,

    @ColumnInfo(name = "averageRating")
    var averageRating: String?,

    @ColumnInfo(name = "startDate")
    var startDate: String?,

    @ColumnInfo(name = "endDate")
    var endDate: String?,

    @ColumnInfo(name = "ratingRank")
    var ratingRank: Int?,

    @ColumnInfo(name = "status")
    var status: String?,

    @ColumnInfo(name = "coverImage")
    var coverImage: String?,

    @ColumnInfo(name = "posterImage")
    var posterImage: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean?
)