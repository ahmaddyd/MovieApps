package com.jetpack.movies.api

import com.jetpack.movies.data.remote.response.DetailsMoviesResponse
import com.jetpack.movies.data.remote.response.MovieResponse
import com.jetpack.movies.data.remote.response.DetailsTvShowsResponse
import com.jetpack.movies.data.remote.response.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataConfig {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailsMoviesResponse>

    @GET("tv/popular")
    fun getPopularTvs(@Query("api_key") apiKey: String): Call<TvResponse>

    @GET("tv/{id}")
    fun getTvDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailsTvShowsResponse>
}