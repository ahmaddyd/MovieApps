package com.jetpack.movies.ui.fragment.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.MoviesEntity
import com.jetpack.movies.utils.DataDummy
import com.jetpack.movies.viewmodel.MoviesFavouriteViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
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
class MoviesFavouriteViewModelTest {
    private lateinit var viewModel: MoviesFavouriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MoviesTvShowsRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MoviesEntity>>

    @Before
    fun setUp() {
        viewModel = MoviesFavouriteViewModel(movieTvRepository)
    }

    @Test
    fun `getFavorites Should be Successful`() {
        val expected = MutableLiveData<PagedList<MoviesEntity>>()
        expected.value = PagedTestDataSources.snapshot(DataDummy.generateDummyMovies())

        `when`(movieTvRepository.getFavoriteMovie()).thenReturn(expected)

        viewModel.getFavorites().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavorites().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavorites Should be Successful but Data empty`() {
        val expected = MutableLiveData<PagedList<MoviesEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        `when`(movieTvRepository.getFavoriteMovie()).thenReturn(expected)

        viewModel.getFavorites().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getFavorites().value?.size
        Assert.assertTrue(
            "Size Data Should be 0, Actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    class PagedTestDataSources private constructor(private val items: List<MoviesEntity>) :
        PositionalDataSource<MoviesEntity>() {
        companion object {
            fun snapshot(items: List<MoviesEntity> = listOf()): PagedList<MoviesEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

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
    }
}