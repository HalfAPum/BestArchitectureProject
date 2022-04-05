package com.example.pagingsample.datasource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pagingsample.datasource.local.dao.base.BaseDaoTest
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.utils.EmulatedData
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@SmallTest
class PassengerDaoTest : BaseDaoTest<Passenger, PassengerDao>() {

    override val singleItem = EmulatedData.passenger
    override val itemList = EmulatedData.passengerList

    override fun Passenger.transform() = copy(name = "SURELY TEST DATA!$%^")

}