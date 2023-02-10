package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListAnimeResponse
import retrofit2.http.GET

interface ApiService {
    @GET("trending/anime")
    suspend fun getAnime(): ListAnimeResponse
}