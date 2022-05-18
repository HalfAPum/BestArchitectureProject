package com.example.pagingsample.datasource.local.helper

import androidx.annotation.WorkerThread
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.model.RemoteKey
import javax.inject.Inject

open class RemoteMediatorDaoHelper<T : Any> @Inject constructor(
    private val saveDataWithRemoteKeysDaoHelper: SaveItemsWithRemoteKeysDaoHelper<T>,
    private val cleanerDaoHelper: ClearAllItemsAndKeysDaoHelper<T>,
    private val remoteKeyDao: RemoteKeyDao,
) {

    @WorkerThread
    suspend fun clearTables() {
        cleanerDaoHelper.clearTables()
    }

    @WorkerThread
    suspend fun save(items: List<T>, remoteKeys: List<RemoteKey>) {
        saveDataWithRemoteKeysDaoHelper.save(items, remoteKeys)
    }

    @WorkerThread
    suspend fun getById(id: Long): RemoteKey {
        return remoteKeyDao.getById(id)
    }

}