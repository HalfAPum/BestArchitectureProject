package com.example.pagingsample.loader

import com.example.pagingsample.utils.SuspendCallback
import com.example.pagingsample.utils.TypedCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FlowLoader<T> @Inject constructor(
//    private val saveDaoHelper: SaveDaoHelper<T>,
) {

    //TODO move dispatcher to constructor
    suspend fun flow(
        serverRequest: SuspendCallback<T?>,
        databaseFlow: TypedCallback<Flow<T>>,
    ) = withContext(Dispatchers.IO) {

        serverRequest.invoke()?.let { serverData ->
//            saveDaoHelper.save(serverData)
        }

        databaseFlow.invoke()
    }

}