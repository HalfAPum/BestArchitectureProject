package com.example.pagingsample.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagingsample.datasource.local.helper.GetPagingSourceDaoHelper
import com.example.pagingsample.datasource.paging.BaseRemoteMediator
import com.example.pagingsample.model.local.character.Character
import javax.inject.Inject
import javax.inject.Singleton

//TODO REMOVE SINGLETON HERE ITS BAD)
@Singleton
class RickAndMortyRepository @Inject constructor(
    private val pagingConfig: PagingConfig,
    private val remoteMediator: BaseRemoteMediator<Character>,
    private val getPagingSourceDaoHelper: GetPagingSourceDaoHelper<Character>,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getCharactersPagingData() = Pager(
        config = pagingConfig,
        remoteMediator = remoteMediator,
        pagingSourceFactory = { getPagingSourceDaoHelper.getPagingSource() }
    ).flow

}