package com.example.pagingsample.repository

import com.example.pagingsample.datasource.paging.PagerWrapper
import com.example.pagingsample.model.interfaces.Identifiable
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PagingRepository<T : Identifiable> @Inject constructor(
    private val pagerWrapper: PagerWrapper<T>
) {

    fun getItemPagingFlow() = pagerWrapper.flow

}