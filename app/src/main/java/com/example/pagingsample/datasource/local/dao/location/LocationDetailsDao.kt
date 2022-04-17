package com.example.pagingsample.datasource.local.dao.location

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseLoadSingleItemFlowDao
import com.example.pagingsample.model.location.LocationDetails
import com.example.pagingsample.model.location.LocationWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDetailsDao : BaseDao<LocationDetails>, BaseLoadSingleItemFlowDao<LocationWithDetails> {

    @Query("DELETE FROM LocationDetails")
    @JvmSuppressWildcards
    override suspend fun clear()

    @Query("SELECT * FROM LocationDetails")
    @JvmSuppressWildcards
    override suspend fun getAll(): List<LocationDetails>

    @Query("SELECT * FROM Location WHERE id = :id")
    @Transaction
    @JvmSuppressWildcards
    override fun flow(id: String): Flow<LocationWithDetails>

}