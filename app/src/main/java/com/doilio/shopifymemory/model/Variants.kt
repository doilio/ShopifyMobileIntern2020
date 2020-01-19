package com.doilio.shopifymemory.model

import com.google.gson.annotations.SerializedName


data class Variants (

    @SerializedName("id") val id : Long,
    @SerializedName("product_id") val product_id : Long,
    @SerializedName("title") val title : String,
    @SerializedName("price") val price : Double,
    @SerializedName("sku") val sku : String,
    @SerializedName("position") val position : Int,
    @SerializedName("inventory_policy") val inventory_policy : String,
    @SerializedName("compare_at_price") val compare_at_price : String,
    @SerializedName("fulfillment_service") val fulfillment_service : String,
    @SerializedName("inventory_management") val inventory_management : String,
    @SerializedName("option1") val option1 : String,
    @SerializedName("option2") val option2 : String,
    @SerializedName("option3") val option3 : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("taxable") val taxable : Boolean,
    @SerializedName("barcode") val barcode : String,
    @SerializedName("grams") val grams : Int,
    @SerializedName("image_id") val image_id : String,
    @SerializedName("weight") val weight : Double,
    @SerializedName("weight_unit") val weight_unit : String,
    @SerializedName("inventory_item_id") val inventory_item_id : Long,
    @SerializedName("inventory_quantity") val inventory_quantity : Int,
    @SerializedName("old_inventory_quantity") val old_inventory_quantity : Int,
    @SerializedName("requires_shipping") val requires_shipping : Boolean,
    @SerializedName("admin_graphql_api_id") val admin_graphql_api_id : String
)