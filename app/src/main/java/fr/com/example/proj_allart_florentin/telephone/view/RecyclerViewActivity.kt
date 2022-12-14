package fr.com.example.proj_allart_florentin.telephone.view

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.com.example.proj_allart_florentin.databinding.ActivityRecyclerViewBinding
import fr.com.example.proj_allart_florentin.telephone.model.MyTelephoneForRecyclerView
import fr.com.example.proj_allart_florentin.telephone.model.TelephoneDataSample
import fr.com.example.proj_allart_florentin.telephone.viewmodel.TelephoneViewModel
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var adapter: TelephoneAdapter
    private lateinit var viewModel: TelephoneViewModel

    private val androidVersionListObserver = Observer<List<MyTelephoneForRecyclerView>> {
        adapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TelephoneViewModel::class.java]

        adapter = TelephoneAdapter() { item, view ->
            onItemClick(item, view)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.recyclerView.adapter = adapter

        binding.addItemButton.setOnClickListener { addRandomTelephone() }
        binding.deleteAllItemButton.setOnClickListener { deleteAllTelephone() }
    }

    private fun addRandomTelephone() {
        val arrayMarque = arrayOf("Google", "Xiaomi", "Samsung", "Iphone")
        val rdmMarque = Random.nextInt(0, 4)
        val random = Random.nextInt(0, 1000)

        viewModel.insertTelephone(
            "${arrayMarque[rdmMarque]} $random",
            if (arrayMarque[rdmMarque] != "Iphone") arrayMarque[rdmMarque] else "Apple",
            "https://picsum.photos/200"
        )
    }

    private fun deleteAllTelephone() {
        viewModel.deleteAllTelephone()
    }

    override fun onStart() {
        super.onStart()
        viewModel.telephoneList.observe(this, androidVersionListObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.telephoneList.observe(this, androidVersionListObserver)
    }

    private fun onItemClick(objectDataSample: TelephoneDataSample, view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, objectDataSample.nom, Toast.LENGTH_SHORT).show()
    }


}