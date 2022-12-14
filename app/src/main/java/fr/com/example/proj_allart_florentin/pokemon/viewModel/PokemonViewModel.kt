package fr.com.example.proj_allart_florentin.pokemon.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import fr.com.example.proj_allart_florentin.pokemon.MyPokemonForRecyclerView
import fr.com.example.proj_allart_florentin.pokemon.PokemonHeader
import fr.com.example.proj_allart_florentin.pokemon.PokemonUi
import fr.com.example.proj_allart_florentin.pokemon.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val pokemonRepository: PokemonRepository by lazy { PokemonRepository() }
    val pokemonList: LiveData<List<MyPokemonForRecyclerView>> =
        pokemonRepository.selectAllPokemon().map { list ->
            list.toMyPokemonForRecyclerView()
        }


    fun fetchNewPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.fetchData()
        }
    }


    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.deleteAllPokemon()
        }
    }


private fun List<PokemonUi>.toMyPokemonForRecyclerView(): List<MyPokemonForRecyclerView> {
    val result = mutableListOf<MyPokemonForRecyclerView>()

    groupBy {
        it.generation
    }.forEach { (generation, items) ->
        result.add(PokemonHeader(generation.toString()))
        result.addAll(items)

    }
    return result
}
}