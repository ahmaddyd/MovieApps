package com.jetpack.movies.data.remote.response

import com.google.gson.annotations.SerializedName

data class DetailsTvShowsResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("first_air_date")
    val firstAirDate: String,
    @field:SerializedName("vote_average")
    val voteAverage: Double,
    @field:SerializedName("poster_path")
    val posterPath: String
)