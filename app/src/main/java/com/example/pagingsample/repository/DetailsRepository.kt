package com.example.pagingsample.repository

import com.example.pagingsample.datasource.remote.helper.ItemApiHelper

//import dagger.hilt.android.scopes.ViewModelScoped
//import javax.inject.Inject

class DetailsRepository<D : Any> constructor(
    private val itemApiHelper: ItemApiHelper<*, D>,
) {

    suspend fun getFlowByItemId(id: Long) =
        itemApiHelper.load(id) ?: throw Exception()

}