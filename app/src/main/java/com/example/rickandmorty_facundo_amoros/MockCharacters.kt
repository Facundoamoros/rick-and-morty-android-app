package com.example.rickandmorty_facundo_amoros

import com.example.rickandmorty_facundo_amoros.model.Character
import com.example.rickandmorty_facundo_amoros.model.Location
import com.example.rickandmorty_facundo_amoros.model.Origin

object MockCharacters {

    val list = listOf(
        Character(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            gender = "Male",
            origin = Origin("Earth (C-137)", ""),
            location = Location("Citadel of Ricks", ""),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        ),
        Character(
            id = 2,
            name = "Morty Smith",
            status = "Alive",
            species = "Human",
            gender = "Male",
            origin = Origin("Earth (C-137)", ""),
            location = Location("Earth (Replacement Dimension)", ""),
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
        ),
        Character(
            id = 3,
            name = "Summer Smith",
            status = "Alive",
            species = "Human",
            gender = "Female",
            origin = Origin("Earth (Replacement Dimension)", ""),
            location = Location("Earth (Replacement Dimension)", ""),
            image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg"
        )
    )
}
