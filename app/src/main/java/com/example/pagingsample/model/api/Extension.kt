package com.example.pagingsample.model.api

import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.model.local.character.Character
import com.example.pagingsample.model.local.character.CharacterLocation

fun PassengerNetwork.toPassenger(): Passenger {
    return Passenger(id, name, trips)
}

fun List<PassengerNetwork>.toPassengerList() = map { it.toPassenger()}

fun CharacterNetwork.toCharacter(): Character {
    return Character(id.toString(), name, status, species, type, gender,
        location.toCharacterLocation(), image, created)
}

fun CharacterLocationNetwork.toCharacterLocation(): CharacterLocation {
    return CharacterLocation(name, url)
}

fun List<CharacterNetwork>.toCharacterList() = map { it.toCharacter()}