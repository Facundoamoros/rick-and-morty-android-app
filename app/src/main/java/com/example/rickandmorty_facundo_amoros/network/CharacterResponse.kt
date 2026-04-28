package com.example.rickandmorty_facundo_amoros.model

// Esta clase representa la respuesta completa que devuelve la API
data class CharacterResponse(

    // Información de paginación (cuántas páginas hay, siguiente página, etc.)
    val info: Info,

    // Lista de personajes que viene dentro del JSON
    val results: List<Character>
)

// Clase que representa la info de paginación
data class Info(
    val count: Int,   // cantidad total de personajes
    val pages: Int,   // cantidad total de páginas
    val next: String?, // URL de la próxima página (puede ser null)
    val prev: String?  // URL de la página anterior (puede ser null)
)