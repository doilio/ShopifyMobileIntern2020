package com.doilio.shopifymemory.fragments.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doilio.shopifymemory.model.Products
import com.doilio.shopifymemory.model.ProductsResponse
import com.doilio.shopifymemory.network.ShopifyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameViewModel : ViewModel() {

    private var _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _products = MutableLiveData<List<Products>>()
    val products: LiveData<List<Products>>
        get() = _products

    init {
        getProducts()
    }

    private fun getProducts() {

        ShopifyApi.retrofitService.getProducts().enqueue(object : Callback<ProductsResponse> {
            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                _response.value = "Failure: ${t.message}"
            }

            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                if (response.body() != null) {
                    val products: List<Products> = response.body()!!.products

                    _products.value = products
                    //_response.value = "We have ${products.size} Products"
                }

            }
        })
        _response.value = "Shopify Api"
    }

}