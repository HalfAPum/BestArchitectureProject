package com.example.pagingsample.datasource.local.helper

import androidx.paging.PagingSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.RemoteKey
import javax.inject.Inject

/**
 * WARNING!!!
 * Use this classes only with entities that have default entity name.
 */

open class SaveItemsWithRemoteKeysDaoHelper<T : Any> @Inject constructor(
    private val itemDao: BaseDao<T>,
    private val remoteKeyDao: BaseDao<RemoteKey>,
    private val transactionManager: ITransactionManager,
) {

    suspend fun save(items: List<T>, remoteKeys: List<RemoteKey>) {
        validateInput(items, remoteKeys)

        transactionManager.runInTransaction {
            itemDao.insertItems(items)
            remoteKeyDao.insertItems(remoteKeys)
        }
    }

    //Todo move to extensions(utils)
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

open class ClearAllItemsAndKeysDaoHelper<T> @Inject constructor(
    private val itemDao: ClearAllItemsDaoHelper<T>,
    private val remoteKeyDao: ClearAllItemsDaoHelper<RemoteKey>,
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
    classType: Class<T>
) : Helper<T>(classType) {

    override val query: String = "SELECT * FROM $table"

    fun getPagingSource(): PagingSource<Int, T> {
        return itemDao.getPagingSource(sqliteQuery)
    }

}

class ClearAllItemsDaoHelper<T> @Inject constructor(
    private val itemDao: BaseDao<T>,
    classType: Class<T>
) : Helper<T>(classType) {

    override val query = "DELETE FROM $table"

    suspend fun clear() {
        itemDao.clear(sqliteQuery)
    }

}

class GetAllItemsDaoHelper<T> @Inject constructor(
    private val itemDao: BaseDao<T>,
    classType: Class<T>
) : Helper<T>(classType) {

    override val query = "SELECT * FROM $table"

    suspend fun getAll() : List<T> {
        return itemDao.getAll(sqliteQuery)
    }

}

abstract class Helper<T>(private val classType: Class<T>) {

    protected abstract val query: String

    protected val table: String
        get() = classType.simpleName

    protected val sqliteQuery: SimpleSQLiteQuery
        get() = SimpleSQLiteQuery(query)

}