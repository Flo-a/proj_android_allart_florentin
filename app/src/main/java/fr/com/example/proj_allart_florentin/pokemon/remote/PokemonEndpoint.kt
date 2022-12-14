package fr.com.example.proj_allart_florentin.pokemon.remote

import fr.com.example.proj_allart_florentin.pokemon.PokemonRetrofit
import retrofit2.http.*



interface PokemonEndpoint {
    //Permet d'avoir un pokémon aléatoirement avec son id
    @GET("pokemon/{id}")
    suspend fun getRandomPokemon(@Path("id") id: Int?) : PokemonRetrofit
}
