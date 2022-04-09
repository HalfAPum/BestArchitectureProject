package com.example.pagingsample.datasource.remote.api

import com.apollographql.apollo3.api.Query
import com.example.pagingsample.utils.PagingQueryCallback
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseApi<T : Query.Data> @Inject constructor(
    private val graphQLExecutor: GraphQLExecutor,
    private val pagingQuery: PagingQueryCallback<T>
) : PagingApi<T> {

    /**
     * Result of [graphQLExecutor] is always [T] type
     * because [pagingQuery] is [Query] with generic [T].
     */
    override suspend fun getPagingItems(page: Int) : T? {
        return graphQLExecutor.executeQuery(pagingQuery(page)) as T?
    }

}