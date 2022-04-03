package com.example.pagingsample.datasource.paging

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pagingsample.EmulatedData
import com.example.pagingsample.datasource.paging.api.MockRickAndMortyApi
import com.example.pagingsample.datasource.paging.base.BaseRemoteMediatorTest
import com.example.pagingsample.datasource.remote.helper.CharacterPagingApiHelper
import com.example.pagingsample.model.local.character.Character
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@SmallTest
class CharacterRemoteMediatorTest : BaseRemoteMediatorTest<Character>() {

    override val itemList = EmulatedData.characterList

    override fun initPagingApiHelper(data: List<Character>, throwError: Boolean) {
        pagingApiHelper = CharacterPagingApiHelper(MockRickAndMortyApi(data, throwError))
    }

    override fun initRemoteMediator() {
        baseRemoteMediator = CharacterRemoteMediator(saveDataWithRemoteKeysDaoHelper, remoteKeyDao,
            cleanerDaoHelper, pagingApiHelper as CharacterPagingApiHelper, loadDispatcher)
    }

}