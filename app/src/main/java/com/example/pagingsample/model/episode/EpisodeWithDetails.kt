package com.example.pagingsample.model.episode

data class EpisodeWithDetails(
    val episode: Episode,
    val detailsWithCharacters: EpisodeDetailsWithCharacters,
)