package com.example.pagingsample.datasource.remote.api.query

import com.example.CharacterByIdQuery
import com.example.CharactersPagingQuery
import com.example.pagingsample.datasource.remote.api.query.base.BaseApiQuery
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.character.CharacterWithDetails
import com.example.pagingsample.utils.TypedQuery
import javax.inject.Inject

class CharacterApiQuery @Inject constructor() : BaseApiQuery<Character> {

    override val pagingQuery: TypedQuery<Int> = { CharactersPagingQuery(it) }

    override val itemByIdQuery: TypedQuery<Long> = { CharacterByIdQuery(it.toString()) }

}

class CharacterDetailsApiQuery @Inject constructor() : BaseApiQuery<CharacterWithDetails> {

    override val pagingQuery: TypedQuery<Int> = { CharactersPagingQuery(it) }

    override val itemByIdQuery: TypedQuery<Long> = { CharacterByIdQuery(it.toString()) }

}