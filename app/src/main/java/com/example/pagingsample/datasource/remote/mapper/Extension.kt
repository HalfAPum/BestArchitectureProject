package com.example.pagingsample.datasource.remote.mapper

import com.example.*
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.character.CharacterDetails
import com.example.pagingsample.model.character.CharacterWithDetails
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.episode.EpisodeDetails
import com.example.pagingsample.model.episode.EpisodeDetailsWithCharacters
import com.example.pagingsample.model.episode.EpisodeWithDetails
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.model.location.LocationDetails
import com.example.pagingsample.model.location.LocationWithDetails

fun CharactersPagingQuery.Data.map() = characters.results.mapCharacters()

private fun List<CharactersPagingQuery.Result>.mapCharacters() = map { it.toCharacter() }

private fun CharactersPagingQuery.Result.toCharacter(): Character {
    return Character(id.toLong(), name, image, status, gender)
}

fun CharacterByIdQuery.Data.map() = character?.map()

private fun CharacterByIdQuery.Character.map() = CharacterWithDetails(
    Character(id.toLong(), name, image, status, gender),
    CharacterDetails(id.toLong(), created, location?.id?.toLong()),
)

fun LocationsPagingQuery.Data.map() = locations.results.mapLocations()

private fun List<LocationsPagingQuery.Result>.mapLocations() = map { it.toLocation() }

private fun LocationsPagingQuery.Result.toLocation(): Location {
    return Location(id.toLong(), name)
}

fun LocationByIdQuery.Data.map() = location?.map()

private fun LocationByIdQuery.Location.map() = LocationWithDetails(
    Location(id.toLong(), name),
    LocationDetails(id, type, dimension),
)

fun EpisodesPagingQuery.Data.map() = episodes.results.mapEpisodes()

private fun List<EpisodesPagingQuery.Result>.mapEpisodes() = map { it.toEpisode() }

private fun EpisodesPagingQuery.Result.toEpisode(): Episode {
    return Episode(id.toLong(), name, air_date)
}

fun EpisodeByIdQuery.Data.map() = episode?.map()

private fun EpisodeByIdQuery.Episode.map() = EpisodeWithDetails(
    Episode(id.toLong(), name, air_date),
    EpisodeDetailsWithCharacters(
        EpisodeDetails(id, created, episode),
        characters.mapCharacter()
    )
)

private fun List<EpisodeByIdQuery.Character?>.mapCharacter() =
    filterNotNull().map { Character(it.id.toLong(), image = it.image) }