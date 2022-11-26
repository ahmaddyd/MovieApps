package com.jetpack.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.MoviesEntity

class MoviesFavouriteViewModel(private val movieTvRepository: MoviesTvShowsRepository) :
    ViewModel() {
    fun getFavorites(): LiveData<PagedList<MoviesEntity>> = movieTvRepository.getFavoriteMovie()
    fun setFavorite(movieEntity: MoviesEntity) {
        val newState = !movieEntity.favorited
        movieTvRepository.setFavoriteMovie(movieEntity, newState)
    }
}