package com.jetpack.movies.datasource

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.data.room.database.MoviesTvShowsDAO

class LocalDataSource private constructor(private val mMovieTvDAO: MoviesTvShowsDAO) {
    fun getDataMovies(): DataSource.Factory<Int, MoviesEntity> = mMovieTvDAO.getMovie()
    fun getFavoritedMovie(): DataSource.Factory<Int, MoviesEntity> =
        mMovieTvDAO.getFavoritedMovie()

    fun getMovieId(id: Int): LiveData<MoviesEntity> = mMovieTvDAO.getMovieId(id)
    fun insertMovies(movies: List<MoviesEntity>) = mMovieTvDAO.insertMovies(movies)
    fun updateFavoriteMovie(movie: MoviesEntity, newState: Boolean) {
        movie.favorited = newState
        mMovieTvDAO.updateMovie(movie)
    }

    fun getDataTvs(): DataSource.Factory<Int, TvShowsEntity> = mMovieTvDAO.getTv()
    fun getFavoritedTv(): DataSource.Factory<Int, TvShowsEntity> = mMovieTvDAO.getFavoritedTv()
    fun getTvId(id: Int): LiveData<TvShowsEntity> = mMovieTvDAO.getTvId(id)
    fun insertTvs(tvs: List<TvShowsEntity>) = mMovieTvDAO.insertTvs(tvs)
    fun updateFavoriteTv(tv: TvShowsEntity, newState: Boolean) {
        tv.favorited = newState
        mMovieTvDAO.updateTv(tv)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(movieTvDAO: MoviesTvShowsDAO): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieTvDAO).apply {
                INSTANCE = this
            }
    }
}