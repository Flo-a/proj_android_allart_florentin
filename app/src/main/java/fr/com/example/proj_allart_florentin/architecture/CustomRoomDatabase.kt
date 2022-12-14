package fr.com.example.proj_allart_florentin.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.com.example.proj_allart_florentin.telephone.dao.TelephoneDao
import fr.com.example.proj_allart_florentin.telephone.model.LocalDataSourceSample
import fr.com.example.proj_allart_florentin.pokemon.PokemonRoom
import fr.com.example.proj_allart_florentin.pokemon.dao.PokemonDao


@Database(
    entities = [
        LocalDataSourceSample::class,
        PokemonRoom::class
    ],
    version = 3,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {
    abstract fun telephoneDao(): TelephoneDao
    abstract fun pokemonDao(): PokemonDao
}
