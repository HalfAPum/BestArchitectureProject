package com.example.pagingsample.datasource.local.helper

import kotlinx.coroutines.flow.Flow

interface SaveDaoHelper<T> { suspend fun save(item: T) }

interface LoadFlowDaoHelper<T> { fun flow(id: String) : Flow<T> }