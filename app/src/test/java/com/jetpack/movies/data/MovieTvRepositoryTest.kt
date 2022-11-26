package com.jetpack.movies.data

import androidx.paging.DataSource
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.jetpack.movies.datasource.LocalDataSource
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.datasource.RemoteDataSource
import com.jetpack.movies.utils.AppExecutors
import com.jetpack.movies.utils.DataDummy
import com.jetpack.movies.utils.LiveDataTestUtil
import com.jetpack.movies.utils.PagedListUtil
import com.jetpack.movies.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieTvRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieTvRepository = FakeMovieTvRepository(remote, local, appExecutors)
    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].id
    private val tvResponses = DataDummy.generateRemoteDummyTvs()
    private val tvId = tvResponses[0].id

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getPopularMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getDataMovies()).thenReturn(dataSourceFactory)
        movieTvRepository.getPopularMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getDataMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }


    @Suppress("UNCHECKED_CAST")
    @Test
    fun getPopularTvs() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsEntity>
        `when`(local.getDataTvs()).thenReturn(dataSourceFactory)
        movieTvRepository.getPopularTvs()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvs()))
        verify(local).getDataTvs()
        assertNotNull(tvEntities.data)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getFavoritedMovie()).thenReturn(dataSourceFactory)
        movieTvRepository.getFavoriteMovie()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoritedMovie()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun getFavoriteTv() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsEntity>
        `when`(local.getFavoritedTv()).thenReturn(dataSourceFactory)
        movieTvRepository.getFavoriteTv()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvs()))
        verify(local).getFavoritedTv()
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyEntity = MutableLiveData<MoviesEntity>()
        dummyEntity.value = DataDummy.generateMovieById(movieId)
        `when`(local.getMovieId(movieId)).thenReturn(dummyEntity)

        val movieEntities = LiveDataTestUtil.getValue(movieTvRepository.getMovieDetail(movieId))
        verify(local).getMovieId(movieId)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses[0].title, movieEntities.data?.title)
        assertEquals(movieResponses[0].overview, movieEntities.data?.overview)
        assertEquals(movieResponses[0].releaseDate, movieEntities.data?.releaseDate)
        assertEquals(movieResponses[0].voteAverage, movieEntities.data?.voteAverage)
        assertEquals(movieResponses[0].posterPath, movieEntities.data?.posterPath)
    }

    @Test
    fun getDetailTv() {
        val dummyEntity = MutableLiveData<TvShowsEntity>()
        dummyEntity.value = DataDummy.generateTvById(tvId)
        `when`(local.getTvId(tvId)).thenReturn(dummyEntity)

        val tvEntities = LiveDataTestUtil.getValue(movieTvRepository.getTvDetail(tvId))
        verify(local).getTvId(tvId)
        assertNotNull(tvEntities.data)
        assertEquals(tvResponses[0].name, tvEntities.data?.name)
        assertEquals(tvResponses[0].overview, tvEntities.data?.overview)
        assertEquals(tvResponses[0].firstAirDate, tvEntities.data?.firstAirDate)
        assertEquals(tvResponses[0].voteAverage, tvEntities.data?.voteAverage)
        assertEquals(tvResponses[0].posterPath, tvEntities.data?.posterPath)
    }
}