package fr.com.example.proj_allart_florentin.pokemon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

sealed class MyPokemonForRecyclerView()

data class PokemonHeader(
    val header: String
) : MyPokemonForRecyclerView()

//Pour UI
data class PokemonUi(
    val nom_pokemon: String,
    val num_pokedex: Int,
    val img_pokemon: String,
    val generation: Int
): MyPokemonForRecyclerView()

/** Object use for room */
@Entity(tableName = "pokemon")
data class PokemonRoom(
    @ColumnInfo(name = "nom_pokemon")
    val nom_pokemon: String,
    @ColumnInfo(name = "num_pokedex")
    val num_pokedex: Int,
    @ColumnInfo(name = "img_pokemon")
    val img_pokemon: String,
    @ColumnInfo(name = "generation")
    val generation: Int,



) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


// API Utiliser: https://pokebuildapi.fr/api/v1
/** Object use for retrofit */
data class PokemonRetrofit(
    @Expose
    @SerializedName("name")
    val nom_pokemon: String,

    @Expose
    @SerializedName("pokedexId")
    val num_pokedex: Int,

    @Expose
    @SerializedName("image")
    val img_pokemon: String,

    @Expose
    @SerializedName("apiGeneration")
    val generation: Int,
)
