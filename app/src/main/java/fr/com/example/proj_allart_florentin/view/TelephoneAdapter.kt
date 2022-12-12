package fr.com.example.proj_allart_florentin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import fr.com.example.proj_allart_florentin.databinding.FooterMarqueBinding
import fr.com.example.proj_allart_florentin.databinding.HeaderMarqueBinding
import fr.com.example.proj_allart_florentin.databinding.ItemCustomRecyclerBinding
import fr.com.example.proj_allart_florentin.model.TelephoneDataFooterSample
import fr.com.example.proj_allart_florentin.model.MyTelephoneForRecyclerView
import fr.com.example.proj_allart_florentin.model.TelephoneDataHeaderSample
import fr.com.example.proj_allart_florentin.model.TelephoneDataSample

private val diffItemUtils = object : DiffUtil.ItemCallback<MyTelephoneForRecyclerView>() {

    override fun areItemsTheSame(
        oldItem: MyTelephoneForRecyclerView, newItem: MyTelephoneForRecyclerView
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MyTelephoneForRecyclerView, newItem: MyTelephoneForRecyclerView
    ): Boolean {
        return oldItem == newItem
    }
}

class TelephoneAdapter(
    private val onItemClick: (quoteUi: TelephoneDataSample, view: View) -> Unit,
) : ListAdapter<MyTelephoneForRecyclerView, RecyclerView.ViewHolder>(diffItemUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        MyItemType.ROW.type -> {
            TelephoneViewHolder(
                ItemCustomRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), onItemClick
            )
        }


        MyItemType.HEADER.type -> {
            TelephoneHeaderViewHolder(
                HeaderMarqueBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
        MyItemType.FOOTER.type -> {
            TelephoneFooterViewHolder(
                FooterMarqueBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
        else -> throw RuntimeException("Wrong view type received $viewType")
    }


    class TelephoneViewHolder(
        private val binding: ItemCustomRecyclerBinding,
        onItemClick: (objectDataSample: TelephoneDataSample, view: View) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var ui: TelephoneDataSample

        init {
            binding.root.setOnClickListener {
                onItemClick(ui, itemView)
            }
        }

        fun bind(TelephoneDataSample: TelephoneDataSample) {
            ui = TelephoneDataSample
            binding.itemRecyclerViewNom.text = TelephoneDataSample.nom
            binding.itemRecyclerViewMarque.text = "${TelephoneDataSample.marque}"
        }
    }

    class TelephoneHeaderViewHolder(
        private val binding: HeaderMarqueBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(TelephoneDataHeaderSample: TelephoneDataHeaderSample) {
            binding.itemRecyclerViewHeader.text = TelephoneDataHeaderSample.header
        }
    }

    class TelephoneFooterViewHolder(
        private val binding: FooterMarqueBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(telephoneFooterViewHolder: TelephoneDataFooterSample) {
            binding.itemRecyclerViewFooter.text = telephoneFooterViewHolder.footer
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TelephoneDataSample -> MyItemType.ROW.type
            is TelephoneDataHeaderSample -> MyItemType.HEADER.type
            is TelephoneDataFooterSample -> MyItemType.FOOTER.type
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            MyItemType.ROW.type -> (holder as TelephoneViewHolder).bind(getItem(position) as TelephoneDataSample)
            MyItemType.HEADER.type -> (holder as TelephoneHeaderViewHolder).bind(
                getItem(
                    position
                ) as TelephoneDataHeaderSample
            )
            MyItemType.FOOTER.type -> (holder as TelephoneFooterViewHolder).bind(
                getItem(
                    position
                ) as TelephoneDataFooterSample
            )
            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }
}

enum class MyItemType(val type: Int) {
    ROW(0), HEADER(1),FOOTER(2)
}

