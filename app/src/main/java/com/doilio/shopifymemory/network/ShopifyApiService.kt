package com.doilio.shopifymemory.network

import com.doilio.shopifymemory.model.Products
import com.doilio.shopifymemory.model.ProductsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL = "https://shopicruit.myshopify.com/"
private const val PAGE = "1"
private const val ACCESS_TOKEN = "c32313df0d0ef512ca64d5b336a0d7c6"

private val retrofit = Retrofit.Builder()
    //.addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ShopifyApiService {

    @Headers("Accept: application/json")
    @GET("admin/products.json")
    fun getProducts(
        @Query("page") page: String = PAGE,
        @Query("access_token") access_token: String = ACCESS_TOKEN
    ): Call<ProductsResponse>


}

object ShopifyApi {

    val retrofitService: ShopifyApiService by lazy {
        retrofit.create(ShopifyApiService::class.java)
    }
}