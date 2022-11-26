package com.jetpack.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.TvShowsEntity

class TvShowsFavouriteViewModel(private val movieTvRepository: MoviesTvShowsRepository) :
    ViewModel() {
    fun getFavorites(): LiveData<PagedList<TvShowsEntity>> = movieTvRepository.getFavoriteTv()
    fun setFavorite(tvEntity: TvShowsEntity) {
        val newState = !tvEntity.favorited
        movieTvRepository.setFavoriteTv(tvEntity, newState)
    }
}