package com.jetpack.movies.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.data.room.entity.TvShowsEntity

@Database(entities = [MoviesEntity::class, TvShowsEntity::class], version = 1, exportSchema = false)
abstract class DatabaseMoviesTvShows : RoomDatabase() {
    abstract fun movieTvDAO(): MoviesTvShowsDAO

    companion object {
        @Volatile
        private var INSTANCE: DatabaseMoviesTvShows? = null
        fun getInstance(context: Context): DatabaseMoviesTvShows =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseMoviesTvShows::class.java,
                    "movie.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}