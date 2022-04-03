package com.example.pagingsample.datasource.paging

import androidx.paging.ExperimentalPagingApi
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.helper.ClearAllItemsAndKeysDaoHelper
import com.example.pagingsample.datasource.local.helper.SaveItemsWithRemoteKeysDaoHelper
import com.example.pagingsample.datasource.paging.base.BaseRemoteMediator
import com.example.pagingsample.datasource.remote.api.AirlineApi
import com.example.pagingsample.datasource.remote.helper.PassengerPagingApiHelper
import com.example.pagingsample.model.local.Passenger
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PassengerRemoteMediator @Inject constructor(
    saveDataDaoHelper: SaveItemsWithRemoteKeysDaoHelper<Passenger>,
    remoteKeyDao: RemoteKeyDao,
    clearTablesDaoHelper: ClearAllItemsAndKeysDaoHelper<Passenger>,
    passengerPagingApiHelper: PassengerPagingApiHelper,
    loadDispatcher: CoroutineDispatcher,
) : BaseRemoteMediator<Passenger>(
    saveDataDaoHelper,
    remoteKeyDao,
    clearTablesDaoHelper,
    passengerPagingApiHelper,
    loadDispatcher
) {

    override val startPage: Int = AirlineApi.PASSENGERS_PAGING_START

}