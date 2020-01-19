package com.doilio.shopifymemory.model

import com.google.gson.annotations.SerializedName

data class ProductsResponse(

    @SerializedName("products") val products: List<Products>,
    @SerializedName("errors") val errors : String
)