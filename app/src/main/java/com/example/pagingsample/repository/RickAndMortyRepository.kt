package com.example.pagingsample.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagingsample.datasource.local.dao.CharacterDao
import com.example.pagingsample.datasource.paging.CharacterRemoteMediator
import com.example.pagingsample.datasource.remote.api.RickAndMortyApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyRepository @Inject constructor(
    private val characterRemoteMediator: CharacterRemoteMediator,
    private val characterDao: CharacterDao,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getCharactersPagingData() = Pager(
        config = PagingConfig(
            pageSize = RickAndMortyApi.CHARACTERS_LOAD_SIZE,
            enablePlaceholders = false,
        ),
        remoteMediator = characterRemoteMediator,
        pagingSourceFactory = { characterDao.getAll() }
    ).flow

}