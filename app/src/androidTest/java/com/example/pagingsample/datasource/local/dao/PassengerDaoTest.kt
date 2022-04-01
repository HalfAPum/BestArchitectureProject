package com.example.pagingsample.datasource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pagingsample.datasource.local.dao.base.BaseDaoWithRemoteKeysTest
import com.example.pagingsample.model.local.Passenger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class PassengerDaoTest : BaseDaoWithRemoteKeysTest<Passenger, PassengerDao>() {

    override val singleItem = EmulatedData.passenger
    override val itemList = EmulatedData.passengerList

    override fun initDao() {
        super.initDao()
        dao = db.passengerDao()
    }

    override fun Passenger.transform() = copy(name = "SURELY TEST DATA!$%^")
}