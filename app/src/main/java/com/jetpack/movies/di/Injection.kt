package com.jetpack.movies.di

import android.content.Context
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.datasource.LocalDataSource
import com.jetpack.movies.data.room.database.DatabaseMoviesTvShows
import com.jetpack.movies.datasource.RemoteDataSource
import com.jetpack.movies.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MoviesTvShowsRepository {
        val database = DatabaseMoviesTvShows.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieTvDAO())
        val appExecutors = AppExecutors()
        return MoviesTvShowsRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}