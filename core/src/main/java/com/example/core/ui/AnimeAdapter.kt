package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemAnimeBinding
import com.example.core.domain.model.Anime

class AnimeAdapter : RecyclerView.Adapter<AnimeAdapter.ListViewHolder>() {

    private var listData = ArrayList<Anime>()
    var onItemClick: ((Anime) -> Unit)? = null

    fun setData(newListData: List<Anime>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAnimeBinding.bind(itemView)
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
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}