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
import timber.log.Timber


enum class ShopifyApiStatus { LOADING, ERROR, DONE }

class GameViewModel(val pairs: Int) : ViewModel() {

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

    private val _rightMoves = MutableLiveData<Int>()
    val rightMoves: LiveData<Int>
        get() = _rightMoves
    private val _wrongMoves = MutableLiveData<Int>()
    val wrongMoves: LiveData<Int>
        get() = _wrongMoves
    private val _totalRightMoves = MutableLiveData<Int>()
    val totalRightMoves: LiveData<Int>
        get() = _totalRightMoves


    init {
        _rightMoves.value = 0
        _wrongMoves.value = 0
        _totalRightMoves.value = initializeTotalRightMoves()
        Timber.d("Valor passado: $pairs")
        _status.value = ShopifyApiStatus.LOADING
        getProducts()
    }

    private fun initializeTotalRightMoves(): Int? {
        return when (pairs) {
            2 -> 24
            3 -> 16
            4 -> 12
            else -> -1
        }
    }

    /**
     * Method to get the list from the Shopify Api
     */
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
                    val products: List<Products> = response.body()!!.products
                    _status.value = ShopifyApiStatus.DONE
                    shuffleList(products)
                    _products.value = gameList
                }

            }
        })
    }

    /**
     * Receives the list of products, shuffles all of the list so that the player gets a chance to use all of the products in the API
     * after that we check what game mode the user selected and multiply the list accordingly.
     */
    private fun shuffleList(products: List<Products>) {

        val myList = ArrayList<Products>()
        myList.addAll(products)

        myList.shuffle() // I choose to Shuffle all the list even tho I wont be using all of it. Because I want to have more product diversity on each game
        when (pairs) {
            2 -> {
                //Duplicate list items and shuffle
                val subList = myList.subList(0, 24)
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.shuffle()

            }
            3 -> {
                // Triple list items and shuffle
                val subList = myList.subList(0, 16)
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.shuffle()
            }
            4 -> {
                // Quadruple list items and shuffle
                val subList = myList.subList(0, 12)
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.addAll(subList)
                gameList.shuffle()
            }

        }

    }

    fun incrementRightMoves() {
        _rightMoves.value = _rightMoves.value?.plus(1)
    }

    fun incrementWrongMoves() {
        _wrongMoves.value = _wrongMoves.value?.plus(1)
    }

}