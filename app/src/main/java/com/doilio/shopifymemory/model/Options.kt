package com.doilio.shopifymemory.model

import com.google.gson.annotations.SerializedName

data class Options (

    @SerializedName("id") val id : Int,
    @SerializedName("product_id") val product_id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("position") val position : Int,
    @SerializedName("values") val values : List<String>
)