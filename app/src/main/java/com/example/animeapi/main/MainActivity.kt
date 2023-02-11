package com.example.animeapi.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapi.R
import com.example.animeapi.databinding.ActivityMainBinding
import com.example.animeapi.detail.DetailAnimeActivity
import com.example.core.data.Resource
import com.example.core.ui.AnimeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var broadcastReceiver: BroadcastReceiver
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animeAdapter = AnimeAdapter{ anime ->
            val intent = Intent(this@MainActivity, DetailAnimeActivity::class.java)
            intent.putExtra(DetailAnimeActivity.EXTRA_DATA, anime)
            startActivity(intent)
        }
//        animeAdapter.onItemClick = { selectedData ->
//            val intent = Intent(this@MainActivity, DetailAnimeActivity::class.java)
//            intent.putExtra(DetailAnimeActivity.EXTRA_DATA, selectedData)
//            startActivity(intent)
//        }

        mainViewModel.anime.observe(this@MainActivity) { anime ->
            if (anime != null) {
                when (anime) {
                    is Resource.Loading -> binding.pbMain.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.pbMain.visibility = View.GONE
                        animeAdapter.submitList(anime.data)
                    }
                    is Resource.Error -> {
                        binding.pbMain.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            anime.message ?: getString(R.string.tv_something_wrong)
                    }
                }
            }
        }

        with(binding.rvAnime) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = animeAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> {
                val uri = Uri.parse("animeapi://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerBroadCastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    Intent.ACTION_POWER_CONNECTED -> {
                        Toast.makeText(applicationContext,getString(R.string.toast_power_connected),Toast.LENGTH_SHORT).show()
                        Log.d(MainActivity::class.java.simpleName,"Power Connected")
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        Toast.makeText(applicationContext,getString(R.string.toast_power_not_connected),Toast.LENGTH_SHORT).show()
                        Log.d(MainActivity::class.java.simpleName,"Power Not Connected")
                    }
                }
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStart() {
        super.onStart()
        registerBroadCastReceiver()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }
}