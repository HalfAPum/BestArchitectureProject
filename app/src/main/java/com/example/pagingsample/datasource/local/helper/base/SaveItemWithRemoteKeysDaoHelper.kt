package com.example.pagingsample.datasource.local.helper.base

import com.example.pagingsample.model.local.RemoteKey

interface SaveItemWithRemoteKeysDaoHelper<T> {

    suspend fun save(items: List<T>, remoteKeys: List<RemoteKey>)

}