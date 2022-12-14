package fr.com.example.proj_allart_florentin.pokemon.view

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.com.example.proj_allart_florentin.databinding.ActivityPokemonBinding
import fr.com.example.proj_allart_florentin.pokemon.MyPokemonForRecyclerView
import fr.com.example.proj_allart_florentin.pokemon.PokemonUi
import fr.com.example.proj_allart_florentin.pokemon.viewModel.PokemonViewModel


class PokemonActivity : AppCompatActivity()  {
    private lateinit var viewModel: PokemonViewModel
    private lateinit var binding : ActivityPokemonBinding
    private lateinit var adapter : PokemonAdapter
    private val androidVersionListObserver = Observer<List<MyPokemonForRecyclerView>> {
        adapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        adapter = PokemonAdapter() { item, view ->
            onItemClick(item, view)
        }
        binding.pokemonActivityRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.pokemonActivityRv.adapter = adapter

        binding.pokemonActivityAdd.setOnClickListener {
            viewModel.fetchNewPokemon()
        }

        binding.pokemonActivityDelete.setOnClickListener {
            viewModel.deleteAll()
        }
    }





    override fun onStart() {
        super.onStart()
        viewModel.pokemonList.observe(this, androidVersionListObserver)
    }

    override fun onStop() {
        viewModel.pokemonList.removeObserver(androidVersionListObserver)
        super.onStop()
    }
    private fun onItemClick(pokemonUi: PokemonUi, view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, pokemonUi.nom_pokemon, Toast.LENGTH_SHORT).show()
    }
}

