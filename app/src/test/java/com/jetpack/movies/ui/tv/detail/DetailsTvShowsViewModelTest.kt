package com.jetpack.movies.ui.tv.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.utils.DataDummy
import com.jetpack.movies.viewmodel.DetailsTvShowsViewModel
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
class DetailsTvShowsViewModelTest {
    private lateinit var viewModel: DetailsTvShowsViewModel

    private val dummyTv = DataDummy.generateDummyTvs()[0]

    private val id = dummyTv.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MoviesTvShowsRepository

    @Mock
    private lateinit var observer: Observer<Resource<TvShowsEntity>>

    @Before
    fun setUp() {
        viewModel = DetailsTvShowsViewModel(movieTvRepository)
        id?.let { viewModel.setSelectedTv(it) }
    }

    @Test
    fun `getTvDetail should be success`() {
        val expected = MutableLiveData<Resource<TvShowsEntity>>()
        expected.value = Resource.success(dummyTv)
        `when`(id?.let { movieTvRepository.getTvDetail(it) }).thenReturn(expected)
        viewModel.tvDetail.observeForever(observer)
        verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.tvDetail.value
        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setFavorite should be success`() {
        val expected = MutableLiveData<Resource<TvShowsEntity>>()
        expected.value = Resource.success(dummyTv)
        `when`(id?.let { movieTvRepository.getTvDetail(it) }).thenReturn(expected)
        viewModel.setFavorite()
        viewModel.tvDetail.observeForever(observer)
        verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.tvDetail.value
        assertEquals(expectedValue, actualValue)
    }
}