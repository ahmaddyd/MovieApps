package com.jetpack.movies.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.jetpack.movies.R
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.databinding.ActivityDetailsTvShowsBinding
import com.jetpack.movies.databinding.ContentDetailsTvShowsBinding
import com.jetpack.movies.utils.formatDate
import com.jetpack.movies.utils.gone
import com.jetpack.movies.utils.loadPoster
import com.jetpack.movies.utils.visible
import com.jetpack.movies.viewmodel.DetailsTvShowsViewModel
import com.jetpack.movies.viewmodel.ViewModelFactory
import com.jetpack.movies.vo.Status

class DetailsTvShowsActivity : AppCompatActivity() {
    private lateinit var activityDetailsTvShowsBinding: ActivityDetailsTvShowsBinding
    private lateinit var contentDetailsTvShowsBinding: ContentDetailsTvShowsBinding
    private lateinit var viewModel: DetailsTvShowsViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailsTvShowsBinding = ActivityDetailsTvShowsBinding.inflate(layoutInflater)
        contentDetailsTvShowsBinding = activityDetailsTvShowsBinding.contentDetailsTvShows

        setContentView(activityDetailsTvShowsBinding.root)

        setSupportActionBar(activityDetailsTvShowsBinding.toolbars)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(this, factory)[DetailsTvShowsViewModel::class.java]

        val id = intent.extras?.getInt(EXTRA_TV_SHOW)
        id?.let { tvId ->
            viewModel.setSelectedTv(tvId)
            viewModel.tvDetail.observe(this, {
                when (it.status) {
                    Status.LOADING -> activityDetailsTvShowsBinding.progressBar.visible()

                    Status.SUCCESS -> if (it.data != null) {
                        activityDetailsTvShowsBinding.progressBar.gone()
                        populateTv(it.data)
                    }

                    Status.ERROR -> {
                        activityDetailsTvShowsBinding.progressBar.gone()
                        Toast.makeText(applicationContext, "There is an error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }
    }

    private fun populateTv(tv: TvShowsEntity) {
        contentDetailsTvShowsBinding.apply {
            with(tv) {
                tvName.text = name

                tvFirstAirDate.text = firstAirDate?.formatDate()

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

        viewModel.tvDetail.observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> activityDetailsTvShowsBinding.progressBar.visible()

                    Status.SUCCESS -> if (it.data != null) {
                        activityDetailsTvShowsBinding.progressBar.gone()
                        val state = it.data.favorited

                        setFavoriteState(state)
                    }

                    Status.ERROR -> {
                        activityDetailsTvShowsBinding.progressBar.gone()
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
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }
}