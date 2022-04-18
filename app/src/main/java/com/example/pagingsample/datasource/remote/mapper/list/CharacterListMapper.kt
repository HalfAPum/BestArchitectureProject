package com.example.pagingsample.datasource.remote.mapper.list

import com.example.CharactersPagingQuery
import com.example.pagingsample.datasource.remote.mapper.base.ListMapper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.character.Character
import org.koin.core.annotation.Factory

@Factory
class CharacterListMapper constructor() : ListMapper<CharactersPagingQuery.Data, Character> {

    override fun map(
        serverData: CharactersPagingQuery.Data
    ) = serverData.map()

}