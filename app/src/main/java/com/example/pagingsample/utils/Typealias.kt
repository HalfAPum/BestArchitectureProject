package com.example.pagingsample.utils

typealias SuspendVoidCallback = suspend () -> Unit

typealias VoidCallback = () -> Unit

typealias TypedVoidCallback<T> = (item: T) -> Unit