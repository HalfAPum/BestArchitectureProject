package com.example.pagingsample.datasource.local.dao.character

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseGetPagingSourceDao
import com.example.pagingsample.model.character.Character

@Dao
interface CharacterDao : BaseDao<Character>, BaseGetPagingSourceDao<Character> {

    @Query("DELETE FROM Character")
    @JvmSuppressWildcards
    override suspend fun clear()

    @Query("SELECT * FROM Character")
    @JvmSuppressWildcards
    override fun getPagingSource(): PagingSource<Int, Character>

    @Query("SELECT * FROM Character")
    @JvmSuppressWildcards
    override suspend fun getAll(): List<Character>

}