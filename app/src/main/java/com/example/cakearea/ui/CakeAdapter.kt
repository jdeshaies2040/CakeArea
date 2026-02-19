package com.example.cakearea.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cakearea.R
import com.example.cakearea.databinding.ItemCakeBinding
import com.example.cakearea.model.CakeResponseItem

class CakeAdapter(private val cakeItem: List<CakeResponseItem>) : RecyclerView.Adapter<CakeAdapter.CakeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CakeViewHolder {
        val binding = ItemCakeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CakeViewHolder(binding)
    }

    // Image URL Sample: http://www.villageinn.com/i/pies/profile/carrotcake_main1.jpg
    //                 .load("http://www.villageinn.com/i/pies/profile/"+cakesItem.image)
    override fun onBindViewHolder(
        holder: CakeViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val cakesItem = cakeItem[position]
            tvTitle.text = cakesItem.title
            tvDescription.text = cakesItem.desc

            Glide.with(holder.itemView.context)
                .load(cakesItem.image)
                .placeholder(R.mipmap.ic_launcher)
                .circleCrop()
                .into(ivCake)
        }
    }

    override fun getItemCount(): Int {
        return cakeItem.count()
    }

    class CakeViewHolder (val binding: ItemCakeBinding)
        : RecyclerView.ViewHolder(binding.root)
}