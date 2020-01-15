package com.doilio.shopifymemory.model

import com.google.gson.annotations.SerializedName

data class Products (

    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("body_html") val body_html : String,
    @SerializedName("vendor") val vendor : String,
    @SerializedName("product_type") val product_type : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("handle") val handle : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("published_at") val published_at : String,
    @SerializedName("template_suffix") val template_suffix : String,
    @SerializedName("published_scope") val published_scope : String,
    @SerializedName("tags") val tags : String,
    @SerializedName("admin_graphql_api_id") val admin_graphql_api_id : String,
    @SerializedName("variants") val variants : List<Variants>,
    @SerializedName("options") val options : List<Options>,
    @SerializedName("images") val images : List<Images>,
    @SerializedName("image") val image : Image
)