package com.jetpack.movies.viewAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.databinding.ItemsMoviesBinding
import com.jetpack.movies.ui.activity.DetailsMoviesActivity
import com.jetpack.movies.utils.formatDateToYear
import com.jetpack.movies.utils.loadPoster

class FavouriteMoviesAdapter :
    PagedListAdapter<MoviesEntity, FavouriteMoviesAdapter.FavoriteMovieViewHolder>(
        DIFF_CALLBACK
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val itemsMovieBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    fun getSwipedData(swipedPosition: Int): MoviesEntity? = getItem(swipedPosition)

    inner class FavoriteMovieViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesEntity) {
            with(binding) {
                tvTitleMovies.text = movie.title
                tvReleaseDate.text = movie.releaseDate?.formatDateToYear()
                tvVoteAverage.text = movie.voteAverage.toString()
                movie.posterPath?.let { ivPoster.loadPoster(it) }
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailsMoviesActivity::class.java)
                intent.putExtra(DetailsMoviesActivity.EXTRA_MOVIE, movie.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}