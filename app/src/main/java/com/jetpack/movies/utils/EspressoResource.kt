package com.jetpack.movies.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoResource {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)
    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}