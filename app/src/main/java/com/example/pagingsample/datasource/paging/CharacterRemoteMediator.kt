package com.example.pagingsample.datasource.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingState
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.helper.ClearAllItemsAndKeys
import com.example.pagingsample.datasource.local.helper.SaveItemsWithRemoteKeys
import com.example.pagingsample.datasource.paging.base.BaseRemoteMediator
import com.example.pagingsample.datasource.remote.api.AirlineApi
import com.example.pagingsample.datasource.remote.api.RickAndMortyApi
import com.example.pagingsample.model.api.toCharacterList
import com.example.pagingsample.model.api.toPassengerList
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.model.local.character.Character
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    saveDataDaoHelper: SaveItemsWithRemoteKeys<Character>,
    remoteKeyDao: RemoteKeyDao,
    clearTablesDaoHelper: ClearAllItemsAndKeys<Character>,
    loadDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BaseRemoteMediator<Character>(
    saveDataDaoHelper,
    remoteKeyDao,
    clearTablesDaoHelper,
    loadDispatcher
) {

    override val startPage = RickAndMortyApi.CHARACTERS_PAGING_START

    override suspend fun loadDataFromServer(
        page: Int,
        state: PagingState<Int, Character>
    ): List<Character> {
        val response = rickAndMortyApi.getCharactersPaging(page)
        return response.characters.toCharacterList()
    }

}