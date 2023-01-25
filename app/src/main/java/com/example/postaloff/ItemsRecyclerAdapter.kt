package com.example.postaloff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postaloff.databinding.ItemViewholderBinding

class ItemsRecyclerAdapter :
    RecyclerView.Adapter<ItemsRecyclerAdapter.ItemViewHolder>() {
    val items = mutableListOf<ItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            ItemViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ItemViewHolder(itemView)
        return holder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(private val binding: ItemViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: ItemModel

        fun bind() {
            model = items[adapterPosition]

            binding.tvTracking.text = "Tracking: ${model.tracking}"
            binding.tvPrice.text = "Price: ${model.price}"
            binding.tvDestination.text = "Destination: ${model.destination}"
            binding.tvComments.text = "Comments: ${model.comments}"
            binding.ivItem.loadImage(model.imageUrl)
        }
    }

    fun setData(currency:List<ItemModel>){
        this.items.addAll(currency)
        notifyDataSetChanged()
    }

}