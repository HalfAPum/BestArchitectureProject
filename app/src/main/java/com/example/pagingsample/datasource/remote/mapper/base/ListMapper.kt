package com.example.pagingsample.datasource.remote.mapper.base

import com.apollographql.apollo3.api.Query

interface ListMapper<SERVER: Query.Data, RESULT> {

    fun map(serverData: SERVER) : List<RESULT>

}