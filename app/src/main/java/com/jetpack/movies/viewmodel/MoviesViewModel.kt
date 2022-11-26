package com.jetpack.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.vo.Resource

class MoviesViewModel(private val movieTvRepository: MoviesTvShowsRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> =
        movieTvRepository.getPopularMovies()
}