package com.example.pagingsample.datasource.local.dao.character

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.character.Character

@Dao
interface CharacterDao : BaseDao<Character>, BaseGetPagingSourceDao<Character> {

    @RawQuery(observedEntities = [Character::class])
    override fun getPagingSource(query: SimpleSQLiteQuery): PagingSource<Int, Character>

}