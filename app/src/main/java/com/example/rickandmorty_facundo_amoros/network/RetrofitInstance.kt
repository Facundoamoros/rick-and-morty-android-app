package com.example.rickandmorty_facundo_amoros.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objeto singleton que crea y proporciona la instancia de Retrofit
object RetrofitInstance {

    // URL base de la API de Rick and Morty
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    // Inicialización lazy: se crea solo cuando se usa por primera vez
    val api: RickAndMortyApi by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL) // Dirección base de la API
            .addConverterFactory(GsonConverterFactory.create())
            // Convierte automáticamente JSON en objetos Kotlin
            .build()
            .create(RickAndMortyApi::class.java)
        // Crea la implementación de la interfaz API
    }
}