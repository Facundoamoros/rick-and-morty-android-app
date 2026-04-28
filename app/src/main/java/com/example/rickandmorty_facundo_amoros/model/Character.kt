// Define los modelos de datos que representan la información que viene desde la API

package com.example.rickandmorty_facundo_amoros.model

// Data class que representa un personaje de Rick and Morty
data class Character(
    val id: Int,          // ID único del personaje
    val name: String,     // Nombre del personaje
    val status: String,   // Estado (Alive, Dead, Unknown)
    val species: String,  // Especie (Human, Alien, etc.)
    val gender: String,   // Género del personaje
    val origin: Origin,   // Objeto que representa el origen
    val location: Location, // Objeto que representa la ubicación actual
    val image: String     // URL de la imagen del personaje
)

// Representa el origen del personaje (objeto dentro del JSON)
data class Origin(
    val name: String, // Nombre del origen
    val url: String   // URL del origen
)

// Representa la ubicación actual del personaje (objeto dentro del JSON)
data class Location(
    val name: String, // Nombre de la ubicación
    val url: String   // URL de la ubicación
)
