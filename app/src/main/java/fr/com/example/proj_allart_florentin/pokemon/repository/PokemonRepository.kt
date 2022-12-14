package fr.com.example.proj_allart_florentin.pokemon.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import fr.com.example.proj_allart_florentin.architecture.CustomApplication
import fr.com.example.proj_allart_florentin.architecture.RetrofitBuilder
import fr.com.example.proj_allart_florentin.pokemon.PokemonRetrofit
import fr.com.example.proj_allart_florentin.pokemon.PokemonRoom
import fr.com.example.proj_allart_florentin.pokemon.PokemonUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class PokemonRepository {
    private val mPokemonDao = CustomApplication.instance.mApplicationDatabase.pokemonDao()

    fun selectAllPokemon(): LiveData<List<PokemonUi>> {
        return mPokemonDao.selectAll().map { list ->
            list.toPokemonUI()
        }
    }


    private suspend fun insertPokemon(pokemonRoom: PokemonRoom) =
        withContext(Dispatchers.IO) {
            mPokemonDao.insert(pokemonRoom)
        }

    suspend fun deleteAllPokemon() = withContext(Dispatchers.IO) {
        mPokemonDao.deleteAll()
    }

    suspend fun fetchData() {
        val rdmpkm = Random.nextInt(0, 898)
        insertPokemon(RetrofitBuilder.getPokemon().getRandomPokemon(rdmpkm).toRoom())

    }

}

private fun PokemonRetrofit.toRoom(): PokemonRoom {
    return PokemonRoom(
        nom_pokemon = nom_pokemon,
        num_pokedex = num_pokedex,
        img_pokemon = img_pokemon,
        generation = generation
    )
}

private fun PokemonUi.toRoomObject(): PokemonRoom {
    return PokemonRoom(
        nom_pokemon = nom_pokemon,
        num_pokedex = num_pokedex,
        img_pokemon = img_pokemon,
        generation = generation
    )
}


private fun List<PokemonRoom>.toPokemonUI(): List<PokemonUi> {
    return map { eachItem ->
        PokemonUi(
            nom_pokemon = eachItem.nom_pokemon,
            num_pokedex = eachItem.num_pokedex,
            img_pokemon = eachItem.img_pokemon,
            generation = eachItem.generation
        )
    }

}
