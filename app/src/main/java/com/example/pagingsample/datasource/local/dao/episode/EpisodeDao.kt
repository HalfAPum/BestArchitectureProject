package com.example.pagingsample.datasource.local.dao.episode

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.episode.Episode

@Dao
interface EpisodeDao : BaseDao<Episode>, BaseGetPagingSourceDao<Episode> {

    @RawQuery(observedEntities = [Episode::class])
    override fun getPagingSource(query: SimpleSQLiteQuery): PagingSource<Int, Episode>

}