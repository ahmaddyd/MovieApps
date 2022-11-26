package com.jetpack.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.vo.Resource

class DetailsMoviesViewModel(private val movieTvRepository: MoviesTvShowsRepository) : ViewModel() {
    val id = MutableLiveData<Int>()
    fun setSelectedMovie(id: Int) {
        this.id.value = id
    }

    var movieDetail: LiveData<Resource<MoviesEntity>> = Transformations.switchMap(id) {
        movieTvRepository.getMovieDetail(it)
    }

    fun setFavorite() {
        val movieResource = movieDetail.value

        if (movieResource?.data != null) {
            val newState = !movieResource.data.favorited
            movieTvRepository.setFavoriteMovie(movieResource.data, newState)
        }
    }
}