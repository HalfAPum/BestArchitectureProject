package com.example.pagingsample.datasource.local.dao.character

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.pagingsample.datasource.local.dao.base.BaseDao
import com.example.pagingsample.datasource.local.dao.base.BaseLoadSingleItemFlowDao
import com.example.pagingsample.model.character.CharacterDetails
import com.example.pagingsample.model.character.CharacterWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDetailsDao : BaseDao<CharacterDetails>, BaseLoadSingleItemFlowDao<CharacterWithDetails> {

    @Query("DELETE FROM CharacterDetails")
    @JvmSuppressWildcards
    override suspend fun clear()

    @Query("SELECT * FROM CharacterDetails")
    @JvmSuppressWildcards
    override suspend fun getAll(): List<CharacterDetails>

    @Query("SELECT * FROM Character WHERE id = :id")
    @Transaction
    @JvmSuppressWildcards
    override fun flow(id: String): Flow<CharacterWithDetails>

}