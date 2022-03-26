package com.example.pagingsample.datasource.paging

import androidx.paging.*
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.helper.ClearAllItemsAndKeys
import com.example.pagingsample.datasource.local.helper.SaveItemsWithRemoteKeys
import com.example.pagingsample.datasource.paging.base.BaseRemoteMediator
import com.example.pagingsample.datasource.remote.api.AirlineApi
import com.example.pagingsample.model.api.toPassengerList
import com.example.pagingsample.model.local.Passenger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PassengerRemoteMediator @Inject constructor(
    private val airlineApi: AirlineApi,
    saveDataDaoHelper: SaveItemsWithRemoteKeys<Passenger>,
    remoteKeyDao: RemoteKeyDao,
    clearTablesDaoHelper: ClearAllItemsAndKeys<Passenger>,
    loadDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BaseRemoteMediator<Passenger>(
    saveDataDaoHelper,
    remoteKeyDao,
    clearTablesDaoHelper,
    loadDispatcher
) {

    override val startPage = AirlineApi.PASSENGERS_PAGING_START

    override suspend fun loadDataFromServer(
        page: Int,
        state: PagingState<Int, Passenger>
    ): List<Passenger> {
        val response = airlineApi.getPassengersPaging(page, state.config.pageSize)
        return response.passengerNetworkList.toPassengerList()
    }

}