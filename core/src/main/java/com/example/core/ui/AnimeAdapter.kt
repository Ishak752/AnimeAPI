package com.example.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.data.source.local.entity.AnimeEntity
import com.example.core.databinding.ItemAnimeBinding
import com.example.core.domain.model.Anime

class AnimeAdapter(private val onItemClick: (Anime)->Unit) : ListAdapter<Anime,AnimeAdapter.ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding,onItemClick)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    class ListViewHolder(private val binding: ItemAnimeBinding,val onItemClick: (Anime) -> Unit)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Anime) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.posterImage)
                    .into(ivPoster)
                tvTitle.text = itemView.context.getString(R.string.anime_adapter_title, data.title)
                tvRating.text =
                    itemView.context.getString(R.string.anime_adapter_rating, data.averageRating)
                tvStatus.text =
                    itemView.context.getString(R.string.anime_adapter_status, data.status)
                tvStartDate.text =
                    itemView.context.getString(R.string.anime_adapter_start_date, data.startDate)
                tvEndDate.text =
                    itemView.context.getString(R.string.anime_adapter_end_date, data.endDate)
            }
            itemView.setOnClickListener{
                onItemClick(data)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Anime> =
            object : DiffUtil.ItemCallback<Anime>() {
                override fun areItemsTheSame(oldUser: Anime, newUser: Anime): Boolean {
                    return oldUser.title == newUser.title
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: Anime, newUser: Anime): Boolean {
                    return oldUser == newUser
                }
            }
    }
}