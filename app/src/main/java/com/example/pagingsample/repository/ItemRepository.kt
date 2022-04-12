package com.example.pagingsample.repository

import androidx.paging.ExperimentalPagingApi
import com.example.pagingsample.datasource.paging.PagerWrapper
import com.example.pagingsample.model.interfaces.Identifiable
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
@OptIn(ExperimentalPagingApi::class)
class ItemRepository<T : Identifiable> @Inject constructor(
    private val itemPager: PagerWrapper<T>
) {

    fun getItemPagingFlow() = itemPager.flow
}