package com.jetpack.movies.data.room.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.data.room.entity.TvShowsEntity

@Dao
interface MoviesTvShowsDAO {
    @Query("SELECT * FROM favorite_movie")
    fun getMovie(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM favorite_movie WHERE favorited = 1")
    fun getFavoritedMovie(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM favorite_movie WHERE id = :id")
    fun getMovieId(id: Int): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Update
    fun updateMovie(movie: MoviesEntity)

    @Query("SELECT * FROM favorite_tv")
    fun getTv(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM favorite_tv WHERE favorited = 1")
    fun getFavoritedTv(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM favorite_tv WHERE id = :id")
    fun getTvId(id: Int): LiveData<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(tvs: List<TvShowsEntity>)

    @Update
    fun updateTv(tv: TvShowsEntity)
}