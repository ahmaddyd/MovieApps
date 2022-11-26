@file:Suppress(
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation"
)

package com.jetpack.movies.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jetpack.movies.datasource.LocalDataSource
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.data.remote.ApiResponse
import com.jetpack.movies.datasource.RemoteDataSource
import com.jetpack.movies.data.remote.response.DetailsMoviesResponse
import com.jetpack.movies.data.remote.response.ResultMovie
import com.jetpack.movies.data.remote.response.ResultTv
import com.jetpack.movies.data.remote.response.DetailsTvShowsResponse
import com.jetpack.movies.datasource.MoviesTvShowsDataSource
import com.jetpack.movies.datasource.NetworkResource
import com.jetpack.movies.utils.AppExecutors
import com.jetpack.movies.vo.Resource

class FakeMovieTvRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MoviesTvShowsDataSource {
    override fun getPopularMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object :
            NetworkResource<PagedList<MoviesEntity>, List<ResultMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(1)
                    .setPrefetchDistance(10)
                    .setPageSize(10)
                    .build()

                return LivePagedListBuilder(localDataSource.getDataMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultMovie>>> =
                remoteDataSource.getPopularMovies()

            override fun saveCallResult(data: List<ResultMovie>) {
                val listMovie = ArrayList<MoviesEntity>()
                for (response in data) {
                    response.apply {
                        val movie = MoviesEntity(
                            id, title, overview, releaseDate, voteAverage, posterPath
                        )

                        listMovie.add(movie)
                    }
                }

                localDataSource.insertMovies(listMovie)
            }
        }.asLiveData()
    }

    override fun getPopularTvs(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object : NetworkResource<PagedList<TvShowsEntity>, List<ResultTv>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(1)
                    .setPrefetchDistance(10)
                    .setPageSize(10)
                    .build()

                return LivePagedListBuilder(localDataSource.getDataTvs(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultTv>>> =
                remoteDataSource.getPopularTvs()

            override fun saveCallResult(data: List<ResultTv>) {
                val listTv = ArrayList<TvShowsEntity>()
                for (response in data) {
                    response.apply {
                        val tv = TvShowsEntity(
                            id, name, overview, firstAirDate, voteAverage, posterPath
                        )
                        listTv.add(tv)
                    }
                }

                localDataSource.insertTvs(listTv)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(id: Int): LiveData<Resource<MoviesEntity>> {
        return object : NetworkResource<MoviesEntity, DetailsMoviesResponse>(appExecutors) {
            override fun shouldFetch(data: MoviesEntity?): Boolean =
                data == null

            override fun loadFromDB(): LiveData<MoviesEntity> =
                localDataSource.getMovieId(id)

            override fun createCall(): LiveData<ApiResponse<DetailsMoviesResponse>> =
                remoteDataSource.getMovieDetail(id)

            override fun saveCallResult(data: DetailsMoviesResponse) {
                with(data) {
                    val movieDetail = MoviesEntity(
                        id = id,
                        title = title,
                        overview = overview,
                        releaseDate = releaseDate,
                        voteAverage = voteAverage,
                        posterPath = posterPath,
                        favorited = false
                    )

                    localDataSource.updateFavoriteMovie(movieDetail, false)
                }
            }
        }.asLiveData()
    }

    override fun getTvDetail(id: Int): LiveData<Resource<TvShowsEntity>> {
        return object : NetworkResource<TvShowsEntity, DetailsTvShowsResponse>(appExecutors) {
            override fun shouldFetch(data: TvShowsEntity?): Boolean =
                data == null

            override fun loadFromDB(): LiveData<TvShowsEntity> =
                localDataSource.getTvId(id)

            override fun createCall(): LiveData<ApiResponse<DetailsTvShowsResponse>> =
                remoteDataSource.getTvDetail(id)

            override fun saveCallResult(data: DetailsTvShowsResponse) {
                with(data) {
                    val tvDetail = TvShowsEntity(
                        id = id,
                        name = name,
                        overview = overview,
                        firstAirDate = firstAirDate,
                        voteAverage = voteAverage,
                        posterPath = posterPath,
                        favorited = false
                    )

                    localDataSource.updateFavoriteTv(tvDetail, false)
                }
            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MoviesEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.updateFavoriteMovie(movie, state)
        }

    override fun setFavoriteTv(tv: TvShowsEntity, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.updateFavoriteTv(tv, state)
        }

    override fun getFavoriteMovie(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(1)
            .setPrefetchDistance(10)
            .setPageSize(10)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoritedMovie(), config).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<TvShowsEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(1)
            .setPrefetchDistance(10)
            .setPageSize(10)
            .build()

        return LivePagedListBuilder(localDataSource.getFavoritedTv(), config).build()
    }
}