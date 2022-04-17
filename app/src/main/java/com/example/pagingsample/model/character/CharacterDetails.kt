package com.example.pagingsample.model.character

import com.example.pagingsample.model.interfaces.Identifiable

data class CharacterDetails(
    override val id: String,
    val created: String?,
    val lastLocationId: String?,
) : Identifiable