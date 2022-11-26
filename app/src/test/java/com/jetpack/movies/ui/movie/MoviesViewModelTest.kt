package com.jetpack.movies.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.utils.DataDummy
import com.jetpack.movies.viewmodel.MoviesViewModel
import com.jetpack.movies.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MoviesTvShowsRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(movieTvRepository)
    }

    @Test
    fun `getPopularMovies should be success`() {
        val movies = PagedTestDataSources.snapshot(DataDummy.generateDummyMovies())
        val expected = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        expected.value = Resource.success(movies)
        `when`(movieTvRepository.getPopularMovies()).thenReturn(expected)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(expected.value)
        val expectedValue = expected.value
        val actualValue = viewModel.getMovies().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getPopularMovies should be success but data is empty`() {
        val movies = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        expected.value = Resource.success(movies)
        `when`(movieTvRepository.getPopularMovies()).thenReturn(expected)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(expected.value)
        val actualValueDataSize = viewModel.getMovies().value?.data?.size
        assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    @Test
    fun `getPopularMovies should be error`() {
        val expectedMessage = "Something happen guys!"
        val expected = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        expected.value = Resource.error(expectedMessage, null)
        `when`(movieTvRepository.getPopularMovies()).thenReturn(expected)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(expected.value)
        val actualMessage = viewModel.getMovies().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    class PagedTestDataSources private constructor(private val items: List<MoviesEntity>) :
        PositionalDataSource<MoviesEntity>() {
        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<MoviesEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MoviesEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }

        companion object {
            fun snapshot(items: List<MoviesEntity> = listOf()): PagedList<MoviesEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }
    }
}