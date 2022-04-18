package com.example.pagingsample.datasource.remote.api.base

import com.apollographql.apollo3.api.Query
import com.example.pagingsample.datasource.remote.api.GraphQLExecutor
import com.example.pagingsample.datasource.remote.api.query.base.BaseApiQuery

/**
 * Use generics only for DI purposes.
 */
open class BaseApi<T> constructor(
    private val graphQLExecutor: GraphQLExecutor,
    private val apiQuery: BaseApiQuery<T>,
) : PagingApi<T>, ItemApi<T> {

    override suspend fun getPagingItems(page: Int) : Query.Data? {
        return graphQLExecutor.executeQuery(apiQuery.pagingQuery(page))
    }

    override suspend fun getItemById(id: Long) : Query.Data? {
        return graphQLExecutor.executeQuery(apiQuery.itemByIdQuery(id))
    }

}