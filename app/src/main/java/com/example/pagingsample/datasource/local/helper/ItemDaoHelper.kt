package com.example.pagingsample.datasource.local.helper

import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.datasource.local.dao.CharacterDao
import com.example.pagingsample.datasource.local.dao.PassengerDao
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.helper.base.CleanerDaoHelper
import com.example.pagingsample.datasource.local.helper.base.SaveItemWithRemoteKeysDaoHelper
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.model.local.RemoteKey
import javax.inject.Inject

class SaveItemsWithRemoteKeys <T : Any> @Inject constructor(
    private val itemDao: BaseDao<T>,
    private val remoteKeyDao: RemoteKeyDao,
    private val transactionManager: ITransactionManager,
) : SaveItemWithRemoteKeysDaoHelper<T> {

    override suspend fun save(items: List<T>, remoteKeys: List<RemoteKey>) {
        transactionManager.runInTransaction {
            itemDao.insert(items)
            remoteKeyDao.insert(remoteKeys)
        }
    }
}

class ClearAllItemsAndKeys <T : Any> @Inject constructor(
    private val itemDao: BaseDao<T>,
    private val remoteKeyDao: RemoteKeyDao,
    private val transactionManager: ITransactionManager,
): CleanerDaoHelper {

    override suspend fun clear() {
        transactionManager.runInTransaction {
            itemDao.clear()
            remoteKeyDao.clear()
        }
    }
}