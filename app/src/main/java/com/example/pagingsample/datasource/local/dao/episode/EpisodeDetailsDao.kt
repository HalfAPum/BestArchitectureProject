package com.example.pagingsample.datasource.local.dao.episode

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseLoadSingleItemFlowDao
import com.example.pagingsample.model.episode.EpisodeDetails
import com.example.pagingsample.model.episode.EpisodeWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDetailsDao : BaseDao<EpisodeDetails>, BaseLoadSingleItemFlowDao<EpisodeWithDetails> {

    @Query("DELETE FROM EpisodeDetails")
    @JvmSuppressWildcards
    override suspend fun clear()

    @Query("SELECT * FROM EpisodeDetails")
    @JvmSuppressWildcards
    override suspend fun getAll(): List<EpisodeDetails>

    @Query("SELECT * FROM Episode WHERE id = :id")
    @Transaction
    @JvmSuppressWildcards
    override fun flow(id: String): Flow<EpisodeWithDetails>

}