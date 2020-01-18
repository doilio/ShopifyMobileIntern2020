package com.doilio.shopifymemory.fragments.game

import android.util.Log
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

    private var _matcher = MutableLiveData<Int>()
    val matcher: LiveData<Int>
        get() = _matcher

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
                    //_products.value = products
                    _products.value = gameList
                    //_response.value = "We have ${products.size} Products"
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
        //val gameList = ArrayList<Products>()
        // Duplicar a lista de 10 itens
        gameList.addAll(subList)
        gameList.addAll(subList)
        // Baralhar a lista duplicada com X itens
        gameList.shuffle()

    }

    fun onImageClicked(productId: Long) {

        for (product in gameList) {
            if (product.id == productId) {
                if (!product.cardFace){
                    product.cardFace = true
                }else{
                    Log.d("Status", "This is already True")
                }
            }
        }

    }

/*    fun getSubList(myList: List<Products>): List<Products> {
        val gameList = ArrayList<Products>()
        return when (_matcher.value) {
            2 -> {
                gameList.addAll(myList)
                gameList.addAll(myList)
                gameList.shuffle()
                gameList
            }
            3 -> {
                gameList.addAll(myList)
                gameList.addAll(myList)
                gameList.addAll(myList)
                gameList.shuffle()
                gameList
            }
            4 -> {
                gameList.addAll(myList)
                gameList.addAll(myList)
                gameList.addAll(myList)
                gameList.addAll(myList)
                gameList.shuffle()
                gameList
            }
            else -> ArrayList()
        }
    }*/

}