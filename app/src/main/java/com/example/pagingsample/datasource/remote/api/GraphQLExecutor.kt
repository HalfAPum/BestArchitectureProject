package com.example.pagingsample.datasource.remote.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class GraphQLExecutor @Inject constructor(
    private val apolloClient: ApolloClient
) {

    suspend fun executeQuery(query: Query<*>) : Query.Data? {
        return apolloClient.query(query).execute().data
    }

}