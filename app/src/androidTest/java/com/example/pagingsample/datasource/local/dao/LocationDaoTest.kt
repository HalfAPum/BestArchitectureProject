package com.example.pagingsample.datasource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pagingsample.datasource.local.dao.base.BaseDaoTest
import com.example.pagingsample.datasource.local.dao.location.LocationDao
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.utils.EmulatedData
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@SmallTest
class LocationDaoTest: BaseDaoTest<Location, LocationDao>() {

    override val singleItem = EmulatedData.location
    override val itemList = EmulatedData.locationList

    override fun Location.transform() = copy(name = "TEST VALUE THAT DO NOT REPEAT")

}