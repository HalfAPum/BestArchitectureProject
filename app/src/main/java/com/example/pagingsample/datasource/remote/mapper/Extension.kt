package com.example.pagingsample.datasource.remote.mapper

import com.apollographql.apollo3.api.ApolloResponse
import com.example.CharactersPagingQuery
import com.example.pagingsample.model.local.character.Character

fun ApolloResponse<CharactersPagingQuery.Data>.map() = data?.characters?.results?.map()

fun List<CharactersPagingQuery.Result>.map() = map { it.toCharacter() }

fun CharactersPagingQuery.Result.toCharacter(): Character {
    return Character(id, name)
}
