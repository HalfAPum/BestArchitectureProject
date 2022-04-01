package com.example.pagingsample.datasource.local.dao.base

import com.example.pagingsample.datasource.local.dao.RemoteKeyDao
import com.example.pagingsample.datasource.local.helper.base.IBaseDaoWithRemoteKeysTest

abstract class BaseDaoWithRemoteKeysTest<T : Any, D : BaseDao<T>> : BaseDaoTest<T, D>(),
    IBaseDaoWithRemoteKeysTest<T, D> {

    override lateinit var remoteKeyDao: RemoteKeyDao

    override fun initDao() {
        remoteKeyDao = db.remoteKeyDao()
    }

}