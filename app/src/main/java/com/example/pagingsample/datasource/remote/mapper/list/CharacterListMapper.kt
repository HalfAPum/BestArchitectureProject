package com.example.pagingsample.datasource.remote.mapper.list

import com.example.CharactersPagingQuery
import com.example.pagingsample.datasource.remote.mapper.base.ListMapper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.local.character.Character

object CharacterListMapper : ListMapper<CharactersPagingQuery.Data, Character> {

    override fun map(
        serverData: CharactersPagingQuery.Data
    ) = serverData.map()

}