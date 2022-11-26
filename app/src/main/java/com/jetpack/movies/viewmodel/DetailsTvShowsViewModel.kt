package com.jetpack.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.vo.Resource

class DetailsTvShowsViewModel(private val movieTvRepository: MoviesTvShowsRepository) :
    ViewModel() {
    val id = MutableLiveData<Int>()
    fun setSelectedTv(id: Int) {
        this.id.value = id
    }

    var tvDetail: LiveData<Resource<TvShowsEntity>> = Transformations.switchMap(id) {
        movieTvRepository.getTvDetail(it)
    }

    fun setFavorite() {
        val tvResource = tvDetail.value

        if (tvResource?.data != null) {
            val newState = !tvResource.data.favorited
            movieTvRepository.setFavoriteTv(tvResource.data, newState)
        }
    }
}