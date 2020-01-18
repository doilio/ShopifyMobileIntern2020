package com.doilio.shopifymemory.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.doilio.shopifymemory.databinding.GameItemBinding
import com.doilio.shopifymemory.model.Products

class GameAdapter(private val clickListener: GameListener) :
    ListAdapter<Products, GameAdapter.GameViewHolder>(DiffCallback) {

    class GameViewHolder(private val binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products, clickListener: GameListener) {
            binding.product = product
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Products>() {
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GameItemBinding.inflate(layoutInflater)

        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

}

class GameListener(val clickListener: (productId: Long) -> Unit) {

    fun onClick(product: Products) = clickListener(product.id)

}

