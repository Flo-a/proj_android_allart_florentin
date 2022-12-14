package fr.com.example.proj_allart_florentin.architecture

import com.google.gson.GsonBuilder
import fr.com.example.proj_allart_florentin.pokemon.remote.PokemonEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokebuildapi.fr/api/v1/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
        .build()


    fun getPokemon(): PokemonEndpoint = retrofit.create(PokemonEndpoint::class.java)

}