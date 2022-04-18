package com.example.pagingsample.datasource.remote.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query
import org.koin.core.annotation.Factory

@Factory
open class GraphQLExecutor constructor(
    private val apolloClient: ApolloClient
) {

    suspend fun executeQuery(query: Query<*>) : Query.Data? {
        return apolloClient.query(query).execute().data
    }

}