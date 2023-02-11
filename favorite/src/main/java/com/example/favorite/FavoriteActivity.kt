package com.example.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapi.detail.DetailAnimeActivity
import com.example.animeapi.di.FavoriteModuleDependencies
import com.example.core.ui.AnimeAdapter
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.favorite.di.DaggerFavoriteComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this@FavoriteActivity)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    this@FavoriteActivity,
                    FavoriteModuleDependencies::class.java
                )
            ).build().inject(this@FavoriteActivity)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeAdapter = AnimeAdapter{ anime ->
            val intent = Intent(this@FavoriteActivity, DetailAnimeActivity::class.java)
            intent.putExtra(DetailAnimeActivity.EXTRA_DATA, anime)
            startActivity(intent)
        }

        favoriteViewModel.favoriteAnime.observe(this@FavoriteActivity) { dataAnime ->
            animeAdapter.submitList(dataAnime)
            binding.viewEmpty.root.visibility =
                if (dataAnime.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvFavorite) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = animeAdapter
        }
    }
}