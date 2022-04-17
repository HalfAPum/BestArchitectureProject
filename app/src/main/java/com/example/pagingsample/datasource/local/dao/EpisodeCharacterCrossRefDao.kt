package com.example.pagingsample.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.model.crossref.EpisodeCharacterCrossRef

@Dao
interface EpisodeCharacterCrossRefDao : BaseDao<EpisodeCharacterCrossRef> {

    @Query("DELETE FROM EpisodeCharacterCrossRef")
    @JvmSuppressWildcards
    override suspend fun clear()

    @Query("SELECT * FROM EpisodeCharacterCrossRef")
    @JvmSuppressWildcards
    override suspend fun getAll(): List<EpisodeCharacterCrossRef>
}