package com.jetpack.movies.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jetpack.movies.BuildConfig.API_KEY
import com.jetpack.movies.api.DataApi
import com.jetpack.movies.data.remote.ApiResponse
import com.jetpack.movies.data.remote.response.*
import com.jetpack.movies.utils.EspressoResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun getPopularMovies(): LiveData<ApiResponse<List<ResultMovie>>> {
        EspressoResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<ResultMovie>>>()

        DataApi.getApiService().getPopularMovies(API_KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    Log.d(this@RemoteDataSource.toString(), "Get Popular Movies Success")
                    resultMovie.value =
                        ApiResponse.success(response.body()?.results as List<ResultMovie>)
                    EspressoResource.decrement()
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e(
                        this@RemoteDataSource.toString(),
                        "Get Popular Movies on Failure: ${t.message}"
                    )

                    EspressoResource.decrement()
                }
            })

        return resultMovie
    }

    fun getPopularTvs(): LiveData<ApiResponse<List<ResultTv>>> {
        EspressoResource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<ResultTv>>>()

        DataApi.getApiService().getPopularTvs(API_KEY)
            .enqueue(object : Callback<TvResponse> {
                override fun onResponse(
                    call: Call<TvResponse>,
                    response: Response<TvResponse>
                ) {
                    Log.d(this@RemoteDataSource.toString(), "Get Popular TV Shows Success")
                    resultTv.value = ApiResponse.success(response.body()?.results as List<ResultTv>)
                    EspressoResource.decrement()
                }

                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    Log.e(
                        this@RemoteDataSource.toString(),
                        "Get Popular TV Shows on Failure: ${t.message}"
                    )

                    EspressoResource.decrement()
                }
            })

        return resultTv
    }

    fun getMovieDetail(id: Int): LiveData<ApiResponse<DetailsMoviesResponse>> {
        EspressoResource.increment()
        val resultMovieDetail = MutableLiveData<ApiResponse<DetailsMoviesResponse>>()

        DataApi.getApiService().getMovieDetail(id, API_KEY)
            .enqueue(object : Callback<DetailsMoviesResponse> {
                override fun onResponse(
                    call: Call<DetailsMoviesResponse>,
                    response: Response<DetailsMoviesResponse>
                ) {
                    Log.d(this@RemoteDataSource.toString(), "Get Details Movies Success")
                    resultMovieDetail.value =
                        ApiResponse.success(response.body() as DetailsMoviesResponse)
                    EspressoResource.decrement()
                }

                override fun onFailure(call: Call<DetailsMoviesResponse>, t: Throwable) {
                    Log.e(
                        this@RemoteDataSource.toString(),
                        "Get Details Movies on Failure: ${t.message}"
                    )

                    EspressoResource.decrement()
                }
            })

        return resultMovieDetail
    }

    fun getTvDetail(id: Int): LiveData<ApiResponse<DetailsTvShowsResponse>> {
        EspressoResource.increment()
        val resultTvDetail = MutableLiveData<ApiResponse<DetailsTvShowsResponse>>()

        DataApi.getApiService().getTvDetail(id, API_KEY)
            .enqueue(object : Callback<DetailsTvShowsResponse> {
                override fun onResponse(
                    call: Call<DetailsTvShowsResponse>,
                    response: Response<DetailsTvShowsResponse>
                ) {
                    Log.d(this@RemoteDataSource.toString(), "Get Details Tv Shows Success")
                    resultTvDetail.value =
                        ApiResponse.success(response.body() as DetailsTvShowsResponse)

                    EspressoResource.decrement()
                }

                override fun onFailure(call: Call<DetailsTvShowsResponse>, t: Throwable) {
                    Log.e(this@RemoteDataSource.toString(), "Get Details Tv Shows on Failure: ${t.message}")

                    EspressoResource.decrement()
                }
            })

        return resultTvDetail
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }
}