package com.example.pagingsample.datasource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pagingsample.datasource.local.dao.base.BaseDaoTest
import com.example.pagingsample.datasource.local.dao.episode.EpisodeDao
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.utils.EmulatedData
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@SmallTest
class EpisodeDaoTest: BaseDaoTest<Episode, EpisodeDao>() {

    override val singleItem = EmulatedData.episode
    override val itemList = EmulatedData.episodeList

    override fun Episode.transform() = copy(name = "TEST VALUE THAT DO NOT REPEAT")

}