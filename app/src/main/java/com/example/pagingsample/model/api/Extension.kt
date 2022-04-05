package com.example.pagingsample.model.api

import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.model.local.character.Character
import com.example.pagingsample.model.local.character.CharacterLocation

fun PassengersResponse.map() = passengerNetworkList.toPassengerList()

fun List<PassengerNetwork>.toPassengerList() = map { it.toPassenger()}

fun PassengerNetwork.toPassenger(): Passenger {
    return Passenger(id, name, trips)
}

fun CharacterResponse.map() = characters.toCharacterList()

fun List<CharacterNetwork>.toCharacterList() = map { it.toCharacter()}

fun CharacterNetwork.toCharacter(): Character {
    return Character(id.toString(), name, status, species, type, gender,
        location.toCharacterLocation(), image, created)
}

fun CharacterLocationNetwork.toCharacterLocation(): CharacterLocation {
    return CharacterLocation(name, url)
}

fun CharacterLocation.toCharacterLocationNetwork(): CharacterLocationNetwork {
    return CharacterLocationNetwork(name, url)
}
