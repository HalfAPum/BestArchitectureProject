package com.example.pagingsample.datasource.remote.api.query.base

import com.example.pagingsample.utils.TypedQuery

interface BaseApiQuery<T> {

    val pagingQuery: TypedQuery<Int>

    val itemByIdQuery: TypedQuery<Long>

}