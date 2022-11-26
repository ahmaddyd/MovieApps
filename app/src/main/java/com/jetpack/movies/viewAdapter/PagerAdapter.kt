package com.jetpack.movies.viewAdapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jetpack.movies.R
import com.jetpack.movies.ui.fragment.FavouriteMoviesFragment
import com.jetpack.movies.ui.fragment.FavouriteTvShowsFragment


class PagerAdapter(private val mContext: Context, fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FavouriteMoviesFragment()
            1 -> FavouriteTvShowsFragment()
            else -> Fragment()
        }

    fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getItemCount(): Int = 2

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }
}