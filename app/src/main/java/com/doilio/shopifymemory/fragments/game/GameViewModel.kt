package com.doilio.shopifymemory.fragments.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doilio.shopifymemory.network.ShopifyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameViewModel : ViewModel() {

    private var _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getProducts()
    }

    private fun getProducts() {

        ShopifyApi.retrofitService.getProducts().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: ${t.message}"
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }
        })
        _response.value = "Shopify Api"
    }

}