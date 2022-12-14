package fr.com.example.proj_allart_florentin.pokemon.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.com.example.proj_allart_florentin.pokemon.PokemonRoom

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon ORDER BY generation ASC")
    fun selectAll() : LiveData<List<PokemonRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemonRoom: PokemonRoom)

    @Query("DELETE FROM pokemon")
    fun deleteAll()
}
