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
fun bindImage(imgView: ImageView, url: String?) {

    url?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}