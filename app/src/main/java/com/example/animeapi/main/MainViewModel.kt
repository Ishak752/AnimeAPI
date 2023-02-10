package com.example.animeapi.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val animeUseCase: AnimeUseCase) : ViewModel() {
    val anime = animeUseCase.getAllAnime().asLiveData()
}