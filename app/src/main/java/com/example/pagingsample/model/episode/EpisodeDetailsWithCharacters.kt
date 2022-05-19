package com.example.pagingsample.model.episode

import com.example.pagingsample.model.character.Character

data class EpisodeDetailsWithCharacters(
    val details: EpisodeDetails,
    val characters: List<Character>
)