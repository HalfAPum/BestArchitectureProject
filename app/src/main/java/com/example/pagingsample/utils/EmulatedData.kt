package com.example.pagingsample.utils

import com.example.pagingsample.model.Character
import com.example.pagingsample.model.Episode
import com.example.pagingsample.model.Location
import com.example.pagingsample.model.RemoteKey

object EmulatedData {

    val character = Character("70","Concerto")

    val characterList = listOf(
        Character("815","Punk Rick"),
        Character("354","Toby Matthews"),
        Character("759","Turkey Morty"),
    )
    val location = Location("70","Concerto")

    val locationList = listOf(
        Location("815","Punk Rick"),
        Location("354","Toby Matthews"),
        Location("759","Turkey Morty"),
    )
    val episode = Episode("70","Concerto")

    val episodeList = listOf(
        Episode("815","Punk Rick"),
        Episode("354","Toby Matthews"),
        Episode("759","Turkey Morty"),
    )

    //    val character = Character("70","Concerto","Dead","Humanoid","",
//        "Male", CharacterLocation("unknown",""),"https://rickandmortyapi.com/api/character/avatar/70.jpeg",
//        "2017-11-30T11:31:41.926Z")

//    val characterList = listOf(
//        Character("815","Punk Rick","Dead","Human","",
//            "Male", CharacterLocation("Rick's Memories","https://rickandmortyapi.com/api/location/126"),
//            "https://rickandmortyapi.com/api/character/avatar/815.jpeg", "2021-11-02T15:19:11.398Z"),
//        Character("354","Toby Matthews","Alive","Human","",
//            "Male", CharacterLocation("Earth (Replacement Dimension)","https://rickandmortyapi.com/api/location/20"),
//            "https://rickandmortyapi.com/api/character/avatar/354.jpeg", "2018-01-10T18:02:03.402Z"),
//        Character("759","Turkey Morty","Alive","Animal","Turkey",
//            "Male", CharacterLocation("Earth (Replacement Dimension)","https://rickandmortyapi.com/api/location/20"),
//            "https://rickandmortyapi.com/api/character/avatar/759.jpeg", "2021-10-17T15:01:58.524Z"),
//    )

    val remoteKey = RemoteKey("1", null, 2)

    val remoteKeyList = listOf(
        RemoteKey("2", null, 2),
        RemoteKey("3", null, 2),
        RemoteKey("4", 1, 3),
    )

}