package com.example.pagingsample.utils

import com.apollographql.apollo3.api.Query

typealias SuspendVoidCallback = suspend () -> Unit

typealias VoidCallback = () -> Unit

typealias TypedVoidCallback<T> = (item: T) -> Unit

/**
 * Need [JvmSuppressWildcards] to escape known issue with generics in java.
 */
typealias PagingQueryCallback<T> = (page: @JvmSuppressWildcards Int) -> @JvmSuppressWildcards Query<T>