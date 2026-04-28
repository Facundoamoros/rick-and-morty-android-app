package com.example.rickandmorty_facundo_amoros

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import coil.load

class CharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Carga el layout de detalle (activity_character_detail.xml)
        setContentView(R.layout.activity_character_detail)

        // Referencias a los elementos visuales del XML
        val imgCharacter = findViewById<ImageView>(R.id.imgCharacter)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val tvSpecies = findViewById<TextView>(R.id.tvSpecies)
        val tvGender = findViewById<TextView>(R.id.tvGender)
        val tvOrigin = findViewById<TextView>(R.id.tvOrigin)

        // Recibe los datos enviados desde MainActivity mediante Intent extras
        val name = intent.getStringExtra("name") ?: "Unknown"
        val image = intent.getStringExtra("image") ?: ""
        val status = intent.getStringExtra("status") ?: "Unknown"
        val species = intent.getStringExtra("species") ?: "Unknown"
        val gender = intent.getStringExtra("gender") ?: "Unknown"
        val origin = intent.getStringExtra("origin") ?: "Unknown"

        // Asigna los datos a las vistas (TextView / ImageView)
        tvName.text = name

        // Coil carga la imagen desde una URL
        imgCharacter.load(image) {
            crossfade(true) // transición suave al cargar la imagen
            placeholder(R.drawable.ic_launcher_foreground) // imagen temporal mientras carga
        }

        // Muestra el resto de la información del personaje
        tvStatus.text = "Status: $status"
        tvSpecies.text = "Species: $species"
        tvGender.text = "Gender: $gender"
        tvOrigin.text = "Origin: $origin"
    }
}