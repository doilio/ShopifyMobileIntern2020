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

    private var gameList = ArrayList<Products>()

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
                    shuffleList(products)
                    _products.value = gameList
                }

            }
        })
        _response.value = "Shopify Api"
    }

    private fun shuffleList(products: List<Products>) {

        val myList = ArrayList<Products>()
        myList.addAll(products)

        // Barralhar a lista
        myList.shuffle()
        // Pegar 10 produtos
        val subList = myList.subList(0, 24)
        // Criar lista a ser usada no jogo
        // Duplicar a lista de 10 itens
        gameList.addAll(subList)
        gameList.addAll(subList)
        // Baralhar a lista duplicada com X itens
        gameList.shuffle()

    }

}