package com.jetpack.movies.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.utils.DataDummy
import com.jetpack.movies.viewmodel.DetailsMoviesViewModel
import com.jetpack.movies.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsMoviesViewModelTest {
    private lateinit var viewModel: DetailsMoviesViewModel

    private val dummyMovie = DataDummy.generateDummyMovies()[0]

    private val id = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MoviesTvShowsRepository

    @Mock
    private lateinit var observer: Observer<Resource<MoviesEntity>>

    @Before
    fun setUp() {
        viewModel = DetailsMoviesViewModel(movieTvRepository)
        id?.let { viewModel.setSelectedMovie(it) }
    }

    @Test
    fun `getMovieDetail should be success`() {
        val expected = MutableLiveData<Resource<MoviesEntity>>()
        expected.value = Resource.success(dummyMovie)
        `when`(id?.let { movieTvRepository.getMovieDetail(it) }).thenReturn(expected)
        viewModel.movieDetail.observeForever(observer)
        verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.movieDetail.value
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setFavorite should be success`() {
        val expected = MutableLiveData<Resource<MoviesEntity>>()
        expected.value = Resource.success(dummyMovie)
        `when`(id?.let { movieTvRepository.getMovieDetail(it) }).thenReturn(expected)
        viewModel.setFavorite()
        viewModel.movieDetail.observeForever(observer)
        verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.movieDetail.value
        assertEquals(expectedValue, actualValue)
    }
}