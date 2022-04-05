package com.example.pagingsample.datasource.local.helper

import androidx.paging.PagingSource
import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.local.RemoteKey
import javax.inject.Inject

//Todo remove open or provide interface or put up with it
open class SaveItemsWithRemoteKeysDaoHelper<T : Any> @Inject constructor(
    private val itemDao: BaseDao<T>,
    private val remoteKeyDao: RemoteKeyDao,
    private val transactionManager: ITransactionManager,
) {

    suspend fun save(items: List<T>, remoteKeys: List<RemoteKey>) {
        validateInput(items, remoteKeys)

        transactionManager.runInTransaction {
            itemDao.insertItems(items)
            remoteKeyDao.insertItems(remoteKeys)
        }
    }

    /**
     * Ensure that we save [RemoteKey] for all [T].
     */
    private fun validateInput(
        items: List<T>,
        remoteKeys: List<RemoteKey>
    ) = when {
        items.size != remoteKeys.size -> {
            throw IllegalArgumentException("""
                Remote key list must have save size as item list.
            """.trimIndent())
        }
        else -> null
    }

}

open class ClearAllItemsAndKeysDaoHelper<T : Any> @Inject constructor(
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

open class GetPagingSourceDaoHelper<T : Any> @Inject constructor(
    private val itemDao: BaseGetPagingSourceDao<T>,
) {

    fun getPagingSource(): PagingSource<Int, T> = itemDao.getPagingSource()

}