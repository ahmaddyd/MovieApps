package com.jetpack.movies.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.jetpack.movies.R
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.databinding.ActivityDetailsMoviesBinding
import com.jetpack.movies.databinding.ContentDetailsMoviesBinding
import com.jetpack.movies.utils.formatDate
import com.jetpack.movies.utils.gone
import com.jetpack.movies.utils.loadPoster
import com.jetpack.movies.utils.visible
import com.jetpack.movies.viewmodel.DetailsMoviesViewModel
import com.jetpack.movies.viewmodel.ViewModelFactory
import com.jetpack.movies.vo.Status

class DetailsMoviesActivity : AppCompatActivity() {
    private lateinit var activityDetailsMoviesBinding: ActivityDetailsMoviesBinding
    private lateinit var contentDetailsMoviesBinding: ContentDetailsMoviesBinding
    private lateinit var viewModel: DetailsMoviesViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailsMoviesBinding = ActivityDetailsMoviesBinding.inflate(layoutInflater)
        contentDetailsMoviesBinding = activityDetailsMoviesBinding.contentDetailsMovies

        setContentView(activityDetailsMoviesBinding.root)

        setSupportActionBar(activityDetailsMoviesBinding.toolbars)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(this, factory)[DetailsMoviesViewModel::class.java]

        val id = intent.extras?.getInt(EXTRA_MOVIE)
        id?.let { movieId ->
            viewModel.setSelectedMovie(movieId)
            viewModel.movieDetail.observe(this, {
                when (it.status) {
                    Status.LOADING -> activityDetailsMoviesBinding.progressBar.visible()

                    Status.SUCCESS -> if (it.data != null) {
                        activityDetailsMoviesBinding.progressBar.gone()
                        populateMovie(it.data)
                    }

                    Status.ERROR -> {
                        activityDetailsMoviesBinding.progressBar.gone()
                        Toast.makeText(applicationContext, "There is an error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }
    }

    private fun populateMovie(movie: MoviesEntity) {
        contentDetailsMoviesBinding.apply {
            with(movie) {
                tvTitle.text = title

                tvReleaseDate.text = releaseDate?.formatDate()

                tvSynopsis.text = overview

                tvVoteAverage.text = voteAverage.toString()

                posterPath?.let { ivPoster.loadPoster(it) }

                posterPath?.let { ivHeader.loadPoster(it) }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)

        this.menu = menu

        viewModel.movieDetail.observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> activityDetailsMoviesBinding.progressBar.visible()

                    Status.SUCCESS -> if (it.data != null) {
                        activityDetailsMoviesBinding.progressBar.gone()
                        val state = it.data.favorited

                        setFavoriteState(state)
                    }

                    Status.ERROR -> {
                        activityDetailsMoviesBinding.progressBar.gone()
                        Toast.makeText(applicationContext, "There is an error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            viewModel.setFavorite()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favourited_blue)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favourite_blue)
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}