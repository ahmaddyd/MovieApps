package com.jetpack.movies.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jetpack.movies.R
import com.jetpack.movies.utils.DataDummy
import com.jetpack.movies.utils.EspressoResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val entityMovies = DataDummy.generateDummyMovies()

    private val entityTvShows = DataDummy.generateDummyTvs()

    @get: Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(entityMovies.size)
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_header)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.recyclerView_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(entityTvShows.size)
        )
    }

    @Test
    fun loadDetailTvShows() {
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.recyclerView_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_first_air_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_header)).check(matches(isDisplayed()))
    }

    @Test
    fun loadMoviesFavourite() {
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.recyclerView_movies_favourite)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_movies_favourite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_header)).check(matches(isDisplayed()))
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}