package com.example.pagingsample.datasource.remote.helper

import com.apollographql.apollo3.api.Query
import com.example.pagingsample.datasource.remote.api.BaseApi
import com.example.pagingsample.datasource.remote.mapper.base.ListMapper
import javax.inject.Inject

/**
 * Main purpose is to provide Base API's helpers that loads data
 * and converts it to convenient for app use type.
 */


/**
 * Type [SERVER] means server result.
 * Type [RESULT] means convenient for app use type.
 */
class PagingApiHelper<SERVER : Query.Data, RESULT : Any> @Inject constructor(
    private val baseApi: BaseApi<SERVER>,
    private val mapper: ListMapper<SERVER, RESULT>,
) {

    val pagingStart: Int
        get() = baseApi.pagingStart

    suspend fun load(page: Int): List<RESULT> {
        return loadFromServer(page).mapServerData()
    }

    private suspend fun loadFromServer(page: Int) : SERVER? {
        return baseApi.getPagingItems(page)
    }

    /**
     * It's ok that we convert [SERVER] to [RESULT] as [List] because
     * [SERVER] is wrapper of other [List] of objects, so it converts this
     * objects to [RESULT].
     */
    private fun SERVER?.mapServerData() : List<RESULT> {
        //TODO TEST TO REMOVE ONE EMPTY LIST CALL
        return this?.let {
            mapper.map(it)
        } ?: emptyList()
    }

}