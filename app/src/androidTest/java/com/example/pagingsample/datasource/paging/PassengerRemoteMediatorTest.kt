package com.example.pagingsample.datasource.paging

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pagingsample.datasource.paging.api.MockAirlineApi
import com.example.pagingsample.datasource.paging.base.BaseRemoteMediatorTest
import com.example.pagingsample.datasource.remote.helper.PassengerPagingApiHelper
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.utils.EmulatedData
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@SmallTest
class PassengerRemoteMediatorTest : BaseRemoteMediatorTest<Passenger>() {

    override val itemList = EmulatedData.passengerList

    override fun initPagingApiHelper(data: List<Passenger>, throwError: Boolean) {
        pagingApiHelper = PassengerPagingApiHelper(MockAirlineApi(data, throwError))
    }

    override fun initRemoteMediator() {
        baseRemoteMediator = PassengerRemoteMediator(saveDataWithRemoteKeysDaoHelper, remoteKeyDao,
            cleanerDaoHelper, pagingApiHelper as PassengerPagingApiHelper, loadDispatcher)
    }

}