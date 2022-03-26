package com.example.pagingsample.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseLoadAllDao
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.model.local.character.Character

@Dao
interface PassengerDao : BaseDao<Passenger>, BaseLoadAllDao<Passenger> {

    @Query("DELETE FROM Passenger")
    @JvmSuppressWildcards
    override suspend fun clear()

    @Query("SELECT * FROM Passenger")
    @JvmSuppressWildcards
    override fun getAll(): PagingSource<Int, Passenger>

}