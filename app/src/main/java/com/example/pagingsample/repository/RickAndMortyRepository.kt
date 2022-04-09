package com.example.pagingsample.repository

import androidx.paging.ExperimentalPagingApi
import com.example.pagingsample.datasource.paging.PagerWrapper
import com.example.pagingsample.model.Character
import com.example.pagingsample.model.Episode
import com.example.pagingsample.model.Location
import javax.inject.Inject
import javax.inject.Singleton

//TODO REMOVE SINGLETON HERE ITS BAD)
@Singleton
@OptIn(ExperimentalPagingApi::class)
class RickAndMortyRepository @Inject constructor(
    private val characterPager: PagerWrapper<Character>,
    private val locationPager: PagerWrapper<Location>,
    private val episodePager: PagerWrapper<Episode>,
) {

    fun getCharactersPagingData() = characterPager.flow

    fun getLocationsPagingData() = locationPager.flow

    fun getEpisodesPagingData() = episodePager.flow

}