package com.doilio.shopifymemory

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.doilio.shopifymemory.fragments.game.ShopifyApiStatus

@BindingAdapter("shopifyApiStatus")
fun bindStatus(imgView: ImageView, status: ShopifyApiStatus) {

    when (status) {
        ShopifyApiStatus.LOADING -> {
            imgView.visibility = View.VISIBLE
            imgView.setImageResource(R.drawable.ic_all_inclusive_black_24dp)
        }
        ShopifyApiStatus.DONE -> {
            imgView.visibility = View.GONE
        }
        ShopifyApiStatus.ERROR -> {
            imgView.visibility = View.VISIBLE
            imgView.setImageResource(R.drawable.ic_cloud_off_black_24dp)
        }
    }
}