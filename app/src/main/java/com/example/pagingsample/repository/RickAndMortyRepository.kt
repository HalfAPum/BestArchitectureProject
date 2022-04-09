package com.example.pagingsample.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagingsample.datasource.local.helper.GetPagingSourceDaoHelper
import com.example.pagingsample.datasource.paging.CharacterRemoteMediator
import com.example.pagingsample.datasource.remote.api.CharacterApi
import com.example.pagingsample.model.local.character.Character
import javax.inject.Inject
import javax.inject.Singleton

//TODO REMOVE SINGLETON HERE ITS BAD)
@Singleton
class RickAndMortyRepository @Inject constructor(
    private val characterRemoteMediator: CharacterRemoteMediator,
    private val getPagingSourceDaoHelper: GetPagingSourceDaoHelper<Character>,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getCharactersPagingData() = Pager(
        config = PagingConfig(
            pageSize = CharacterApi.CHARACTERS_LOAD_SIZE,
        ),
        remoteMediator = characterRemoteMediator,
        pagingSourceFactory = { getPagingSourceDaoHelper.getPagingSource() }
    ).flow

}