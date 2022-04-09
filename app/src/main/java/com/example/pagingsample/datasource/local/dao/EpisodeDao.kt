package com.example.pagingsample.datasource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.Episode

@Dao
interface EpisodeDao : BaseDao<Episode>, BaseGetPagingSourceDao<Episode> {

    @Query("DELETE FROM Episode")
    @JvmSuppressWildcards
    override suspend fun clear()

    @Query("SELECT * FROM Episode")
    @JvmSuppressWildcards
    override fun getPagingSource(): PagingSource<Int, Episode>

    @Query("SELECT * FROM Episode")
    @JvmSuppressWildcards
    override suspend fun getAll(): List<Episode>

}