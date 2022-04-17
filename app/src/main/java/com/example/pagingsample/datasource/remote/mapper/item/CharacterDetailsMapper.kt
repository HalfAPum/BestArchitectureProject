package com.example.pagingsample.datasource.remote.mapper.item

import com.example.CharacterByIdQuery
import com.example.pagingsample.datasource.remote.mapper.base.ItemMapper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.character.CharacterWithDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDetailsMapper @Inject constructor() : ItemMapper<CharacterByIdQuery.Data, CharacterWithDetails> {

    override fun map(serverData: CharacterByIdQuery.Data): CharacterWithDetails? {
        return serverData.map()
    }

}