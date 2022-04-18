package com.example.pagingsample.datasource.remote.mapper.item

import com.example.CharacterByIdQuery
import com.example.pagingsample.datasource.remote.mapper.base.ItemMapper
import com.example.pagingsample.datasource.remote.mapper.map
import com.example.pagingsample.model.character.CharacterWithDetails
import org.koin.core.annotation.Factory

//import javax.inject.Inject
//import javax.inject.Singleton

//@Singleton
@Factory
class CharacterDetailsMapper constructor() : ItemMapper<CharacterByIdQuery.Data, CharacterWithDetails> {

    override fun map(serverData: CharacterByIdQuery.Data): CharacterWithDetails? {
        return serverData.map()
    }

}