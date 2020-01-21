package com.doilio.shopifymemory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.doilio.shopifymemory.R
import com.doilio.shopifymemory.model.Products

class GridViewAdapter(private val context: Context, private val list: List<Products>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.game_item, parent, false)

        val gridItem = view.findViewById<ImageView>(R.id.product_image)
        val gridItemText = view.findViewById<TextView>(R.id.card_text)
        val product = list[position]
        if (gridItemText.text == "back") {
            //Glide.with(context).load(R.drawable.slab_back).into(gridItem)
            Glide.with(context).load(product.image.src).into(gridItem) //TEST
        } else {
            Glide.with(context).load(product.image.src).into(gridItem)
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isEnabled(position: Int): Boolean {
       return !list[position].cardFace
    }

    override fun areAllItemsEnabled(): Boolean {
        return true
    }
}