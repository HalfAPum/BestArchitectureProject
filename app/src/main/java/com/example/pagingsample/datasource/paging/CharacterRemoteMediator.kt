package com.example.pagingsample.datasource.paging

import androidx.paging.ExperimentalPagingApi
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.helper.ClearAllItemsAndKeysDaoHelper
import com.example.pagingsample.datasource.local.helper.SaveItemsWithRemoteKeysDaoHelper
import com.example.pagingsample.datasource.paging.base.BaseRemoteMediator
import com.example.pagingsample.datasource.remote.api.RickAndMortyApi
import com.example.pagingsample.datasource.remote.helper.CharacterPagingApiHelper
import com.example.pagingsample.model.local.character.Character
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator @Inject constructor(
    saveDataDaoHelper: SaveItemsWithRemoteKeysDaoHelper<Character>,
    remoteKeyDao: RemoteKeyDao,
    clearTablesDaoHelper: ClearAllItemsAndKeysDaoHelper<Character>,
    characterPagingApiHelper: CharacterPagingApiHelper,
    loadDispatcher: CoroutineDispatcher,
) : BaseRemoteMediator<Character>(
    saveDataDaoHelper,
    remoteKeyDao,
    clearTablesDaoHelper,
    characterPagingApiHelper,
    loadDispatcher
) {

    override val startPage: Int = RickAndMortyApi.CHARACTERS_PAGING_START

}