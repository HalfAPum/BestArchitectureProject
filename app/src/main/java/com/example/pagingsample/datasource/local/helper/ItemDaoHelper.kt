package com.example.pagingsample.datasource.local.helper

import androidx.paging.PagingSource
import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.local.RemoteKey
import javax.inject.Inject

class SaveItemsWithRemoteKeysDaoHelper<T : Any> @Inject constructor(
    private val itemDao: BaseDao<T>,
    private val remoteKeyDao: RemoteKeyDao,
    private val transactionManager: ITransactionManager,
) {

    suspend fun save(items: List<T>, remoteKeys: List<RemoteKey>) {
        transactionManager.runInTransaction {
            itemDao.insert(items)
            remoteKeyDao.insert(remoteKeys)
        }
    }

}

class ClearAllItemsAndKeysDaoHelper<T : Any> @Inject constructor(
    private val itemDao: BaseDao<T>,
    private val remoteKeyDao: RemoteKeyDao,
    private val transactionManager: ITransactionManager,
) {

    suspend fun clearTables() {
        transactionManager.runInTransaction {
            itemDao.clear()
            remoteKeyDao.clear()
        }
    }

}

class GetPagingSourceDaoHelper<T : Any> @Inject constructor(
    private val itemDao: BaseGetPagingSourceDao<T>,
) {

    fun getPagingSource(): PagingSource<Int, T> = itemDao.getPagingSource()

}