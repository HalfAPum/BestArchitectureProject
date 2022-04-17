package com.example.pagingsample.repository

import com.example.pagingsample.datasource.local.helper.LoadFlowDaoHelper
import com.example.pagingsample.datasource.remote.helper.ItemApiHelper
import com.example.pagingsample.loader.FlowLoader
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DetailsRepository<D : Any> @Inject constructor(
    private val itemApiHelper: ItemApiHelper<*, D>,
    private val flowDaoHelper: LoadFlowDaoHelper<D>,
    private val flowLoader: FlowLoader<D>
) {

    suspend fun getFlowByItemId(id: String) =
        flowLoader.flow(
            serverRequest = { itemApiHelper.load(id) },
            databaseFlow = { flowDaoHelper.flow(id) }
        )

}