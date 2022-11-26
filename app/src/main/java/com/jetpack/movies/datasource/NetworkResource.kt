package com.jetpack.movies.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.jetpack.movies.vo.Resource
import com.jetpack.movies.data.remote.ApiResponse
import com.jetpack.movies.data.remote.StatusResponse
import com.jetpack.movies.utils.AppExecutors

abstract class NetworkResource<ResultType, RequestType>(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val databaseSource = loadFromDB()

        result.addSource(databaseSource) { data ->
            result.removeSource(databaseSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(databaseSource)
            } else {
                result.addSource(databaseSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDB(): LiveData<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(databaseSource: LiveData<ResultType>) {

        val apiResponse = createCall()

        result.addSource(databaseSource) { newData ->
            result.value = Resource.loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(databaseSource)
            when (response.status) {
                StatusResponse.SUCCESS ->
                    appExecutors.diskIO().execute {
                        saveCallResult(response.body)
                        appExecutors.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }
                    }

                StatusResponse.EMPTY -> appExecutors.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = Resource.success(newData)
                    }
                }

                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(databaseSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}