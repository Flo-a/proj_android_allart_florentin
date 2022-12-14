package fr.com.example.proj_allart_florentin.pokemon.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.com.example.proj_allart_florentin.databinding.ItemPokemonBinding
import fr.com.example.proj_allart_florentin.databinding.PokemonGenHeaderBinding
import fr.com.example.proj_allart_florentin.pokemon.MyPokemonForRecyclerView
import fr.com.example.proj_allart_florentin.pokemon.PokemonHeader
import fr.com.example.proj_allart_florentin.pokemon.PokemonUi


val diffUtils = object : DiffUtil.ItemCallback<MyPokemonForRecyclerView>() {
    override fun areItemsTheSame(
        oldItem: MyPokemonForRecyclerView,
        newItem: MyPokemonForRecyclerView
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MyPokemonForRecyclerView,
        newItem: MyPokemonForRecyclerView
    ): Boolean {
        return oldItem == newItem
    }

}

class PokemonAdapter(
    private val onItemClick: (pokemonUi: PokemonUi, view: View) -> Unit,
) : ListAdapter<MyPokemonForRecyclerView, RecyclerView.ViewHolder>(diffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        MyItemType.ROW.type -> {
            PokemonViewHolder(
                ItemPokemonBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), onItemClick
            )
        }


        MyItemType.HEADER.type -> {
            PokemonHeaderViewHolder(
                PokemonGenHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        else -> throw RuntimeException("Wrong view type received $viewType")
    }


    class PokemonViewHolder(
        private val binding: ItemPokemonBinding,
        onItemClick: (pokemonUi: PokemonUi, view: View) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var ui: PokemonUi

        init {
            binding.root.setOnClickListener {
                onItemClick(ui, itemView)
            }
        }


        fun bind(pokemonUi: PokemonUi) {
            ui = pokemonUi

            Glide.with(itemView.context)
                .load(pokemonUi.img_pokemon)
                .apply(RequestOptions().override(500, 500))
                .into(binding.pokemonImg)

            binding.pokemonNom.text = pokemonUi.nom_pokemon
            binding.numPokedex.text = "#" + pokemonUi.num_pokedex.toString()

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PokemonUi -> MyItemType.ROW.type
            is PokemonHeader -> MyItemType.HEADER.type

            else -> {
                1
            }
        }
    }

    class PokemonHeaderViewHolder(
        private val binding: PokemonGenHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(PokemonHeader: PokemonHeader) {
            binding.itemPokemonHeader.text = "Génération " + PokemonHeader.header
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            MyItemType.ROW.type -> (holder as PokemonViewHolder).bind(
                getItem(
                    position
                ) as PokemonUi
            )
            MyItemType.HEADER.type -> (holder as PokemonHeaderViewHolder).bind(
                getItem(
                    position
                ) as PokemonHeader
            )
            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }


}


enum class MyItemType(val type: Int) {
    ROW(0), HEADER(1),
}

