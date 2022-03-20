package com.example.pagingsample.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingsample.datasource.remote.api.AirlineApi
import com.example.pagingsample.datasource.remote.api.AirlineApi.Companion.PASSENGERS_LOAD_SIZE
import com.example.pagingsample.datasource.remote.api.AirlineApi.Companion.PASSENGERS_PAGING_START
import com.example.pagingsample.model.api.Passenger
import com.example.pagingsample.model.api.PassengersResponse
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PassengerPagingSource @Inject constructor(
    private val airlineApi: AirlineApi,
) : PagingSource<Int, Passenger>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        val position = params.key ?: PASSENGERS_PAGING_START
        return try {
            val response = airlineApi.getPassengersPaging(position, params.loadSize)
            val passengers = response.data

            val prevKey = if (position == PASSENGERS_PAGING_START) null
                else position - 1

            val nextKey = if (passengers.isNullOrEmpty()) null
                else calculateNextKey(position, params.loadSize)

            LoadResult.Page(
                passengers,
                prevKey,
                nextKey
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Passenger>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    /**
     * Initial load size is 3 * PASSENGERS_LOAD_SIZE
     * So next key of initial load is 4.
     * All other keys is key + 1 (5,6,7,8 etc.)
     */
    private fun calculateNextKey(position: Int, loadSize: Int): Int {
        return position + loadSize / PASSENGERS_LOAD_SIZE
    }

}