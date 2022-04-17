package com.example.pagingsample.datasource.local.helper

import androidx.annotation.WorkerThread
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.model.RemoteKey
import javax.inject.Inject

open class RemoteMediatorDaoHelper<T : Any> @Inject constructor(
    private val saveDataWithRemoteKeysDaoHelper: SaveItemsWithRemoteKeysDaoHelper<T>,
    private val remoteKeyDao: RemoteKeyDao,
    private val cleanerDaoHelper: ClearAllItemsAndKeysDaoHelper<T>,
) {

    @WorkerThread
    suspend fun clearTables() {
        cleanerDaoHelper.clearTables()
    }

    @WorkerThread
    suspend fun getById(id: Long): RemoteKey {
        return remoteKeyDao.getById(id)
    }

    @WorkerThread
    suspend fun save(items: List<T>, remoteKeys: List<RemoteKey>) {
        saveDataWithRemoteKeysDaoHelper.save(items, remoteKeys)
    }

}