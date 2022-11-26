package com.jetpack.movies.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.di.Injection

class ViewModelFactory private constructor(private val movieTvRepository: MoviesTvShowsRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(movieTvRepository) as T
            }

            modelClass.isAssignableFrom(DetailsMoviesViewModel::class.java) -> {
                DetailsMoviesViewModel(movieTvRepository) as T
            }

            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                TvShowsViewModel(movieTvRepository) as T
            }

            modelClass.isAssignableFrom(DetailsTvShowsViewModel::class.java) -> {
                DetailsTvShowsViewModel(movieTvRepository) as T
            }

            modelClass.isAssignableFrom(MoviesFavouriteViewModel::class.java) -> {
                MoviesFavouriteViewModel(movieTvRepository) as T
            }

            modelClass.isAssignableFrom(TvShowsFavouriteViewModel::class.java) -> {
                TvShowsFavouriteViewModel(movieTvRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }
}