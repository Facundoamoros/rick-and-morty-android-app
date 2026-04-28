package com.example.rickandmorty_facundo_amoros

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty_facundo_amoros.adapter.CharacterAdapter
import com.example.rickandmorty_facundo_amoros.model.Character
import com.example.rickandmorty_facundo_amoros.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // RecyclerView donde se muestran los personajes
    private lateinit var rvCharacters: RecyclerView

    // Barra de búsqueda (EditText)
    private lateinit var searchBar: EditText

    // Checkbox para filtrar solo humanos
    private lateinit var filterHuman: CheckBox

    // Adapter que conecta los datos con el RecyclerView
    private lateinit var adapter: CharacterAdapter

    // Lista base con TODOS los personajes cargados
    private var allCharacters = mutableListOf<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Activa modo edge-to-edge (pantalla completa moderna)
        enableEdgeToEdge()

        // Carga el layout principal (activity_main.xml)
        setContentView(R.layout.activity_main)

        // Ajusta padding para evitar que la lista quede debajo de la barra del sistema
        findViewById<RecyclerView>(R.id.rvCharacters)?.let { rv ->
            ViewCompat.setOnApplyWindowInsetsListener(rv) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // Referencias a los elementos del XML
        rvCharacters = findViewById(R.id.rvCharacters)
        searchBar = findViewById(R.id.etSearch)
        filterHuman = findViewById(R.id.cbFilterHuman)

        // Define que el RecyclerView se mostrará como lista vertical
        rvCharacters.layoutManager = LinearLayoutManager(this)

        // Carga todos los personajes desde la API
        loadAllCharacters()

        // Listener que se ejecuta cada vez que cambia el texto del buscador
        searchBar.addTextChangedListener(object : TextWatcher {

            // No usamos estos métodos pero son obligatorios
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            // Cuando el usuario escribe algo se aplican los filtros
            override fun afterTextChanged(s: Editable?) {
                applyFilters()
            }
        })

        // Listener del checkbox (filtrar humanos)
        filterHuman.setOnCheckedChangeListener { _, _ ->
            applyFilters()
        }
    }

    // Función que carga TODOS los personajes desde la API (todas las páginas)
    private fun loadAllCharacters() {

        // Coroutine para ejecutar código asíncrono sin bloquear la UI
        lifecycleScope.launch {
            try {
                val tempList = mutableListOf<Character>()
                var currentPage = 1
                var hasNextPage = true

                // Loop que recorre todas las páginas de la API
                while (hasNextPage) {

                    // Llamada a Retrofit para obtener personajes
                    val response = RetrofitInstance.api.getCharacters(currentPage)

                    // Agrega los resultados a la lista temporal
                    tempList.addAll(response.results)

                    // Si next es null significa que ya no hay más páginas
                    hasNextPage = response.info.next != null

                    currentPage++
                }

                // Guardamos la lista completa
                allCharacters = tempList

                Log.d("API_TEST", "Total characters loaded: ${allCharacters.size}")

                // Creamos el adapter y lo asignamos al RecyclerView
                adapter = CharacterAdapter(allCharacters) { openDetail(it) }
                rvCharacters.adapter = adapter

            } catch (e: Exception) {

                // Si la API falla (ej: HTTP 429) mostramos error en log
                Log.e("API_TEST", "Error fetching characters: ${e.message}")

                // Fallback: usamos lista local para que la app siga funcionando
                allCharacters = MockCharacters.list.toMutableList()
                adapter = CharacterAdapter(allCharacters) { openDetail(it) }
                rvCharacters.adapter = adapter
            }
        }
    }

    // Función que aplica filtros de búsqueda y especie
    private fun applyFilters() {

        // Texto escrito en la barra de búsqueda
        val query = searchBar.text.toString().trim()

        // Partimos siempre de la lista completa
        var filteredList = allCharacters

        // Filtrar por nombre si el usuario escribió algo
        if (query.isNotEmpty()) {
            filteredList = filteredList.filter {
                it.name.contains(query, ignoreCase = true)
            }.toMutableList()
        }

        // Filtrar por especie Human si el checkbox está marcado
        if (filterHuman.isChecked) {
            filteredList = filteredList.filter {
                it.species.equals("Human", ignoreCase = true)
            }.toMutableList()
        }

        // Actualizamos el RecyclerView con la lista filtrada
        rvCharacters.adapter = CharacterAdapter(filteredList) { openDetail(it) }
    }

    // Abre la pantalla de detalle pasando datos del personaje seleccionado
    private fun openDetail(selectedCharacter: Character) {

        val intent = Intent(this@MainActivity, CharacterDetailActivity::class.java).apply {

            // Pasamos datos al DetailActivity mediante extras
            putExtra("name", selectedCharacter.name)
            putExtra("image", selectedCharacter.image)
            putExtra("status", selectedCharacter.status)
            putExtra("species", selectedCharacter.species)
            putExtra("gender", selectedCharacter.gender)
            putExtra("origin", selectedCharacter.origin.name)
        }

        // Inicia la nueva Activity
        startActivity(intent)
    }
}
