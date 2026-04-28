package com.example.rickandmorty_facundo_amoros.network

import com.example.rickandmorty_facundo_amoros.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

// Interfaz que define los endpoints de la API Rick and Morty usando Retrofit
interface RickAndMortyApi {

    // Endpoint para obtener personajes
    // @GET("character") significa que se llama a:
    // https://rickandmortyapi.com/api/character
    @GET("character")
    suspend fun getCharacters(

        // Parámetro de paginación (ej: ?page=2)
        @Query("page") page: Int? = null

    ): CharacterResponse // Retrofit convierte el JSON en este modelo

    // Endpoint para buscar personajes por nombre
    @GET("character")
    suspend fun searchCharacters(

        // Parámetro ?name=rick por ejemplo
        @Query("name") name: String

    ): CharacterResponse
}
