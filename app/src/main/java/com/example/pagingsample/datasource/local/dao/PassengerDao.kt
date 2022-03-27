package com.example.pagingsample.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.local.Passenger

@Dao
interface PassengerDao : BaseDao<Passenger>, BaseGetPagingSourceDao<Passenger> {

    @Query("DELETE FROM Passenger")
    @JvmSuppressWildcards
    override suspend fun clear()

    @Query("SELECT * FROM Passenger")
    @JvmSuppressWildcards
    override fun getPagingSource(): PagingSource<Int, Passenger>

}