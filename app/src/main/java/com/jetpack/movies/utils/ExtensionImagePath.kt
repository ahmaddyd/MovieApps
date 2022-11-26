package com.jetpack.movies.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.movies.BuildConfig
import com.jetpack.movies.R

const val BASE_POSTER = BuildConfig.IMAGE_URL

fun ImageView.loadPoster(path: String) {
    Glide.with(context)
        .load(BASE_POSTER + path)
        .apply(
            RequestOptions
                .placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
        )
        .into(this)
}