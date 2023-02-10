package com.example.animeapi.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.animeapi.R
import com.example.animeapi.databinding.ActivityDetailAnimeBinding
import com.example.core.domain.model.Anime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailAnimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAnimeBinding
    private val detailAnimeViewModel: DetailAnimeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailAnime = intent.getParcelableExtra<Anime>(EXTRA_DATA)
        showDetailAnime(detailAnime)
    }

    private fun showDetailAnime(detailAnime: Anime?) {
        detailAnime.let {
            supportActionBar?.title = detailAnime?.title
            binding.content.tvDetailTitle.text = detailAnime?.title
            binding.content.tvRating.text = detailAnime?.averageRating.toString()
            binding.content.tvRank.text = detailAnime?.ratingRank.toString()
            binding.content.tvStartDate.text = detailAnime?.startDate
            binding.content.tvEndDate.text = detailAnime?.endDate
            binding.content.tvStatus.text = detailAnime?.status
            binding.content.tvDetailSynopsis.text = detailAnime?.synopsis
            binding.content.tvDetailDescription.text = detailAnime?.description
            Glide.with(this@DetailAnimeActivity)
                .load(detailAnime?.coverImage)
                .centerInside()
                .fitCenter()
                .into(binding.ivDetailImage)

            var statusFavorite = detailAnime?.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite!!
                detailAnimeViewModel.setFavoriteAnime(detailAnime!!, statusFavorite!!)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean?) {
        if (statusFavorite == true) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailAnimeActivity, R.drawable.ic_favorited
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailAnimeActivity, R.drawable.ic_favorite
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}