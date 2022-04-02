package com.example.pagingsample.datasource.local.dao.base

import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.helper.base.IBaseDaoWithRemoteKeysTest
import javax.inject.Inject

abstract class BaseDaoWithRemoteKeysTest<T : Any, D : BaseDao<T>> : BaseDaoTest<T, D>(),
    IBaseDaoWithRemoteKeysTest<T, D> {

    @Inject
    override lateinit var remoteKeyDao: RemoteKeyDao

}