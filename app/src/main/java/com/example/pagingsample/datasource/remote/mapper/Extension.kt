package com.example.pagingsample.datasource.remote.mapper

import com.example.CharactersPagingQuery
import com.example.pagingsample.model.local.character.Character

fun CharactersPagingQuery.Data.map() = characters.results.map()

fun List<CharactersPagingQuery.Result>.map() = map { it.toCharacter() }

fun CharactersPagingQuery.Result.toCharacter(): Character {
    return Character(id, name)
}
