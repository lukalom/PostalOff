package com.example.postaloff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postaloff.databinding.ViewpagerLayoutBinding


class VpAdapter(val items:List<Int>) : RecyclerView.Adapter<VpAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(private val binding: ViewpagerLayoutBinding): RecyclerView.ViewHolder(binding.root){
            private var model: Int = 0
        fun bind(){
            model = items[adapterPosition]
            binding.ivVpItems.setImageResource(model)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = ViewpagerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind()
    }
}
