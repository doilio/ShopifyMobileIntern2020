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


enum class ShopifyApiStatus { LOADING, ERROR, DONE }

class GameViewModel : ViewModel() {

    private var _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _products = MutableLiveData<List<Products>>()
    val products: LiveData<List<Products>>
        get() = _products

    private var gameList = ArrayList<Products>()
    private var _status = MutableLiveData<ShopifyApiStatus>()
    val status: LiveData<ShopifyApiStatus>
        get() = _status


//     var gameOptions: Int? = 0

    var gameOptions = MutableLiveData<Int>()
   // val gameOption get() = _gameOption


    init {
        _status.value = ShopifyApiStatus.LOADING
        getProducts()
    }

    private fun getProducts() {

        ShopifyApi.retrofitService.getProducts().enqueue(object : Callback<ProductsResponse> {
            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                _response.value = "Failure: ${t.message}"
                _status.value = ShopifyApiStatus.ERROR
            }

            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                if (response.body() != null) {
                    //_loading.value = false
                    val products: List<Products> = response.body()!!.products
                    _status.value = ShopifyApiStatus.DONE
                    shuffleList(products)
                    _products.value = gameList
                }

            }
        })
        //_response.value = "Shopify Api"
    }

    private fun shuffleList(products: List<Products>) {

        val myList = ArrayList<Products>()
        myList.addAll(products)

/*        // I choose to Shuffle all the list even tho I wont be using all of it. Because I want to have more product diversity on each game
        myList.shuffle()
        // Pegar 10 produtos
        val subList = myList.subList(0, 24)
        // Criar lista a ser usada no jogo
        // Duplicar a lista de 10 itens
        gameList.addAll(subList)
        gameList.addAll(subList)
        // Baralhar a lista duplicada com X itens
        gameList.shuffle()*/
        myList.shuffle()
        Log.d("ViewModel","Opcao:  e ${gameOptions.value}")
        when (gameOptions.value) {
            2 -> {
                //Duplicate list items
                val subList = myList.subList(0, 24)
                // Criar lista a ser usada no jogo
                // Duplicar a lista de 10 itens
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.shuffle()
                getProducts()
            }
            3 -> {
                // Triple list items
                val subList = myList.subList(0, 16)
                // Criar lista a ser usada no jogo
                // Duplicar a lista de 10 itens
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.shuffle()
                getProducts()
            }
            4 -> {
                // Quadruple list items
                // Triple list items
                val subList = myList.subList(0, 12)
                // Criar lista a ser usada no jogo
                // Duplicar a lista de 10 itens
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.shuffle()
                getProducts()
            }
            else ->{
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

    }

}