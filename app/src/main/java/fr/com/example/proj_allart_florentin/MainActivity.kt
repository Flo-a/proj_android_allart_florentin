package fr.com.example.proj_allart_florentin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.com.example.proj_allart_florentin.databinding.ActivityMainBinding
import fr.com.example.proj_allart_florentin.pokemon.view.PokemonActivity
import fr.com.example.proj_allart_florentin.telephone.view.RecyclerViewActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainButtonRecyclerView.setOnClickListener { goToRecyclerView() }
        binding.buttonPokemon.setOnClickListener { goToPokemon() }
    }


    private fun goToRecyclerView() {
        startActivity(Intent(this, RecyclerViewActivity::class.java))
    }

    private fun goToPokemon() {
        startActivity(Intent(this, PokemonActivity::class.java))
    }
}