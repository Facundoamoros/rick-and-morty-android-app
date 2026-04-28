// Adapter para mostrar una lista de personajes en un RecyclerView

package com.example.rickandmorty_facundo_amoros.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty_facundo_amoros.R
import com.example.rickandmorty_facundo_amoros.model.Character

// Adapter que conecta la lista de personajes con el RecyclerView
class CharacterAdapter(
    private val characters: List<Character>, // Lista de datos que vamos a mostrar
    private val onItemClick: (Character) -> Unit // Función que se ejecuta al tocar un item
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    // ViewHolder representa una fila individual del RecyclerView
    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Referencias a los elementos visuales del layout item_character.xml
        val ivCharacter: ImageView = itemView.findViewById(R.id.ivCharacter)
        val tvCharacterName: TextView = itemView.findViewById(R.id.tvCharacterName)
    }

    // Se ejecuta cuando el RecyclerView necesita crear una nueva fila
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {

        // Infla el layout XML que representa cada item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)

        // Devuelve el ViewHolder con la vista inflada
        return CharacterViewHolder(view)
    }

    // Se ejecuta cada vez que hay que mostrar datos en una fila
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        // Obtiene el personaje según la posición
        val character = characters[position]

        // Asigna nombre al TextView
        holder.tvCharacterName.text = character.name

        // Carga imagen desde URL usando Coil
        holder.ivCharacter.load(character.image)

        // Listener de click sobre el item
        holder.itemView.setOnClickListener {
            onItemClick(character) // Llama la función que viene desde MainActivity
        }
    }

    // Cantidad total de elementos en la lista
    override fun getItemCount(): Int = characters.size
}