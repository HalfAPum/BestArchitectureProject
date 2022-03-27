package com.example.pagingsample.datasource.remote.helper.base

/**
 * Main purpose is to provide Base API's helpers that loads data
 * and converts it to convenient for app use type.
 */


/**
 * Type [SERVER] means server result.
 * Type [RESULT] means convenient for app use type.
 */
abstract class PagingApiHelper<SERVER : Any, RESULT : Any> {

    suspend fun load(page: Int): List<RESULT> {
        return loadFromServer(page).mapServerData()
    }

    protected abstract suspend fun loadFromServer(page: Int) : SERVER

    /**
     * It's ok that we convert [SERVER] to [RESULT] as [List] because
     * [SERVER] is wrapper of other [List] of objects, so it converts this
     * objects to [RESULT].
     */
    protected abstract suspend fun SERVER.mapServerData() : List<RESULT>

}