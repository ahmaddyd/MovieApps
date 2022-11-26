package com.jetpack.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.vo.Resource

class TvShowsViewModel(private val movieTvRepository: MoviesTvShowsRepository) : ViewModel() {
    fun getTvs(): LiveData<Resource<PagedList<TvShowsEntity>>> = movieTvRepository.getPopularTvs()
}