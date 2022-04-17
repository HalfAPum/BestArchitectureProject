package com.example.pagingsample.utils

import com.apollographql.apollo3.api.Query

typealias SuspendVoidCallback = suspend () -> Unit

typealias SuspendCallback<T> = suspend () -> T

typealias VoidCallback = () -> Unit

typealias TypedVoidCallback<T> = (item: T) -> Unit

typealias TypedCallback<T> = () -> T

typealias TypedQuery<T> = (value: T) -> Query<*>