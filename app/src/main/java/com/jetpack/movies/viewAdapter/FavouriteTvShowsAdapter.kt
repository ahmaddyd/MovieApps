package com.jetpack.movies.viewAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.databinding.ItemsTvShowsBinding
import com.jetpack.movies.ui.activity.DetailsTvShowsActivity
import com.jetpack.movies.utils.formatDate
import com.jetpack.movies.utils.loadPoster

class FavouriteTvShowsAdapter :
    PagedListAdapter<TvShowsEntity, FavouriteTvShowsAdapter.FavoriteTvViewHolder>(
        DIFF_CALLBACK
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvViewHolder {
        val itemsTvBinding =
            ItemsTvShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvViewHolder(itemsTvBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTvViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowsEntity? = getItem(swipedPosition)

    inner class FavoriteTvViewHolder(private val binding: ItemsTvShowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvShowsEntity) {
            with(binding) {
                tvName.text = tv.name
                tvFirstAirDate.text = tv.firstAirDate?.formatDate()
                tvVoteAverage.text = tv.voteAverage.toString()
                tv.posterPath?.let { ivPoster.loadPoster(it) }
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailsTvShowsActivity::class.java)
                intent.putExtra(DetailsTvShowsActivity.EXTRA_TV_SHOW, tv.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowsEntity>() {
            override fun areItemsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TvShowsEntity,
                newItem: TvShowsEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}