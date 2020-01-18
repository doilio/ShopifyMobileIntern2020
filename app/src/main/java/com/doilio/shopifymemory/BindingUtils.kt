package com.doilio.shopifymemory

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doilio.shopifymemory.adapters.GameAdapter
import com.doilio.shopifymemory.model.Products


@BindingAdapter("productList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Products>?) {

    data?.let {
        val adapter = recyclerView.adapter as GameAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("imageUrl")
//fun bindImage(imgView: ImageView, url: String?) {
fun bindImage(imgView: ImageView, products: Products?) {
    val url = products?.image?.src
    val cardFace = products?.cardFace
    url?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        if (cardFace == true) {
            imgView.setImageResource(R.drawable.slab_back)
        } else {
            Glide.with(imgView.context)
                .load(imgUri)
                .into(imgView)
        }


    }
}