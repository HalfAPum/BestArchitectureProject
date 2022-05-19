package com.example.pagingsample.model.character

import com.example.pagingsample.model.interfaces.Identifiable

data class CharacterDetails(
    override val id: Long,
    val created: String?,
    val lastLocationId: Long?,
) : Identifiable