package fr.com.example.proj_allart_florentin.view

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.com.example.proj_allart_florentin.databinding.ActivityRecyclerViewBinding
import fr.com.example.proj_allart_florentin.databinding.FooterMarqueBinding
import fr.com.example.proj_allart_florentin.model.*

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var adapter: TelephoneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TelephoneAdapter { item, view ->
            onItemClick(item, view)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.recyclerView.adapter = adapter

        adapter.submitList(generateFakeData())

    }

    private fun onItemClick(objectDataSample: TelephoneDataSample, view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
        Toast.makeText(this, objectDataSample.nom, Toast.LENGTH_SHORT).show()
    }

    private fun generateFakeData(): MutableList<MyTelephoneForRecyclerView> {
        val result = mutableListOf<MyTelephoneForRecyclerView>()
        // Create data raw
        mutableListOf(
            TelephoneDataSample("Google pixel 6", "Google", "https://picsum.photos/200"),
            TelephoneDataSample("Google pixel 5", "Google", "https://picsum.photos/200"),
            TelephoneDataSample("Google pixel 6 Pro", "Google", "https://picsum.photos/200"),
            TelephoneDataSample("Google pixel 4", "Google", "https://picsum.photos/200"),
            TelephoneDataSample("Google pixel 3", "Google", "https://picsum.photos/200"),
            TelephoneDataSample("Google pixel 2", "Google", "https://picsum.photos/200"),
            TelephoneDataSample("Google pixel", "Google", "https://picsum.photos/200"),
            TelephoneDataSample("Google pixel 6a", "Google", "https://picsum.photos/200"),
            TelephoneDataSample("Samsung Galaxy S21", "Samsung", "https://picsum.photos/200"),
            TelephoneDataSample("Samsung Galaxy S22", "Samsung", "https://picsum.photos/200"),
            TelephoneDataSample("Xiaomi 4", "Xiaomi", "https://picsum.photos/200"),
            TelephoneDataSample("Xiaomi 5", "Xiaomi", "https://picsum.photos/200"),
        ).groupBy {
            // Split in 2 list, modulo and not
            it.marque
        }.forEach { (marque, items) ->
            result.add(TelephoneDataHeaderSample(" $marque"))
            result.addAll(items)
            result.add(TelephoneDataFooterSample(items.size.toString()))
        }
        return result
    }


}