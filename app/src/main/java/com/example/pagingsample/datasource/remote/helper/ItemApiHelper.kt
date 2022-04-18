@file:Suppress("UNCHECKED_CAST")

package com.example.pagingsample.datasource.remote.helper

import com.apollographql.apollo3.api.Query
import com.example.pagingsample.datasource.remote.api.base.BaseApi
import com.example.pagingsample.datasource.remote.mapper.base.ItemMapper
import com.example.pagingsample.datasource.remote.mapper.base.ListMapper

/**
 * Main purpose is to provide Base API's helpers that loads data
 * and converts it to convenient for app use type.
 */


/**
 * Type [SERVER] means server result.
 * Type [RESULT] means convenient for app use type.
 */
open class PagingApiHelper<SERVER : Query.Data, RESULT : Any> constructor(
    private val pagingApi: BaseApi<RESULT>,
    private val mapper: ListMapper<SERVER, RESULT>,
) : IPagingApiHelper<RESULT> {

    override val pagingStart: Int
        get() = pagingApi.pagingStart

    override suspend fun load(page: Int): List<RESULT> {
        return loadFromServer(page).mapServerData()
    }

    private suspend fun loadFromServer(page: Int) : SERVER? {
        return pagingApi.getPagingItems(page) as? SERVER
    }

    /**
     * It's ok that we convert [SERVER] to [RESULT] as [List] because
     * [SERVER] is wrapper around [List] of objects, so it converts this
     * objects to [List] of [RESULT].
     */
    private fun SERVER?.mapServerData() : List<RESULT> {
        return this?.let {
            mapper.map(it)
        } ?: emptyList()
    }

}

open class ItemApiHelper<SERVER : Query.Data, RESULT : Any> constructor(
    private val itemApi: BaseApi<RESULT>,
    private val mapper: ItemMapper<SERVER, RESULT>
) {

    suspend fun load(id: Long) : RESULT? {
        return loadFromServer(id).mapServerData()
    }

    private suspend fun loadFromServer(id: Long): SERVER? {
        return itemApi.getItemById(id) as? SERVER
    }

    private fun SERVER?.mapServerData(): RESULT? {
        return this?.let { mapper.map(this) }
    }

}