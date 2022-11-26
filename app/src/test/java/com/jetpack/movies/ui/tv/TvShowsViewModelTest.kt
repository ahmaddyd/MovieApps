package com.jetpack.movies.ui.tv


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.jetpack.movies.repository.MoviesTvShowsRepository
import com.jetpack.movies.data.room.entity.TvShowsEntity
import com.jetpack.movies.utils.DataDummy
import com.jetpack.movies.viewmodel.TvShowsViewModel
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
class TvShowsViewModelTest {
    private lateinit var viewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MoviesTvShowsRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowsEntity>>>

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel(movieTvRepository)
    }

    @Test
    fun `getPopularTvs should be success`() {
        val tvs = PagedTestDataSources.snapshot(DataDummy.generateDummyTvs())
        val expected = MutableLiveData<Resource<PagedList<TvShowsEntity>>>()
        expected.value = Resource.success(tvs)

        `when`(movieTvRepository.getPopularTvs()).thenReturn(expected)

        viewModel.getTvs().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvs().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getPopularTvs should be success but data is empty`() {
        val tvs = PagedTestDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TvShowsEntity>>>()
        expected.value = Resource.success(tvs)

        `when`(movieTvRepository.getPopularTvs()).thenReturn(expected)

        viewModel.getTvs().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualValueDataSize = viewModel.getTvs().value?.data?.size
        assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    @Test
    fun `getPopularTvs should be error`() {
        val expectedMessage = "Something happen guys!"
        val expected = MutableLiveData<Resource<PagedList<TvShowsEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        `when`(movieTvRepository.getPopularTvs()).thenReturn(expected)

        viewModel.getTvs().observeForever(observer)
        verify(observer).onChanged(expected.value)

        val actualMessage = viewModel.getTvs().value?.message
        assertEquals(expectedMessage, actualMessage)
    }

    class PagedTestDataSources private constructor(private val items: List<TvShowsEntity>) :
        PositionalDataSource<TvShowsEntity>() {
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

        companion object {
            fun snapshot(items: List<TvShowsEntity> = listOf()): PagedList<TvShowsEntity> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }
    }
}