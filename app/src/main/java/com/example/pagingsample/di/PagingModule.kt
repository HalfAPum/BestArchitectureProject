package com.example.pagingsample.di

import androidx.paging.PagingConfig
import com.example.pagingsample.datasource.remote.api.base.BaseApi
import com.example.pagingsample.datasource.remote.api.base.PagingApi
import com.example.pagingsample.datasource.remote.helper.IPagingApiHelper
import com.example.pagingsample.datasource.remote.helper.PagingApiHelper
import com.example.pagingsample.datasource.remote.mapper.list.CharacterListMapper
import com.example.pagingsample.datasource.remote.mapper.list.EpisodeListMapper
import com.example.pagingsample.datasource.remote.mapper.list.LocationListMapper
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.viewmodel.CharacterPaging1ViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pagingModule = module {
    viewModel { CharacterPaging1ViewModel(get()) }

    factory { Dispatchers.IO }

    factory { PagingConfig(pageSize = PagingApi.LOAD_SIZE) }

    genericFactory<IPagingApiHelper<Character>> { PagingApiHelper(genericGet(), CharacterListMapper()) }
    genericFactory<IPagingApiHelper<Location>> { PagingApiHelper(get(), LocationListMapper()) }
    genericFactory<IPagingApiHelper<Episode>> { PagingApiHelper(get(), EpisodeListMapper()) }

    genericFactory { BaseApi<Character>(get(), genericGet()) }
}