package com.example.pagingsample.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.model.RemoteKey

@Dao
interface RemoteKeyDao : BaseDao<RemoteKey> {

    @Query("SELECT * FROM RemoteKey WHERE id = :id")
    suspend fun getById(id: Long): RemoteKey

}