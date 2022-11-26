package com.jetpack.movies.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.vo.Resource

interface MoviesTvShowsDataSource {
    fun getPopularMovies(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getMovieDetail(id: Int): LiveData<Resource<MoviesEntity>>

    fun setFavoriteMovie(movie: MoviesEntity, state: Boolean)

    fun getFavoriteMovie(): LiveData<PagedList<MoviesEntity>>

    fun getPopularTvs(): LiveData<Resource<PagedList<TvShowsEntity>>>

    fun getTvDetail(id: Int): LiveData<Resource<TvShowsEntity>>

    fun setFavoriteTv(tv: TvShowsEntity, state: Boolean)

    fun getFavoriteTv(): LiveData<PagedList<TvShowsEntity>>
}