package com.jetpack.movies.ui.fragment.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.utils.DataDummy
import com.jetpack.movies.viewmodel.TvShowsFavouriteViewModel
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
class TvShowsFavouriteViewModelTest {
    private lateinit var viewModel: TvShowsFavouriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MoviesTvShowsRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowsEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowsFavouriteViewModel(movieTvRepository)
    }

    @Test
    fun `getFavorites should be success`() {
        val expected = MutableLiveData<PagedList<TvShowsEntity>>()
        expected.value = PagedTestDataSources.snapshot(DataDummy.generateDummyTvs())

        `when`(movieTvRepository.getFavoriteTv()).thenReturn(expected)

        viewModel.getFavorites().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getFavorites().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getFavorites should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<TvShowsEntity>>()
        expected.value = PagedTestDataSources.snapshot()

        `when`(movieTvRepository.getFavoriteTv()).thenReturn(expected)

        viewModel.getFavorites().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getFavorites().value?.size
        Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    class PagedTestDataSources private constructor(private val items: List<TvShowsEntity>) :
        PositionalDataSource<TvShowsEntity>() {
        companion object {
            fun snapshot(items: List<TvShowsEntity> = listOf()): PagedList<TvShowsEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<TvShowsEntity>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(
            params: LoadRangeParams,
            callback: LoadRangeCallback<TvShowsEntity>
        ) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}