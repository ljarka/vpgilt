package com.jarka.vpgilt.main.network

import com.google.gson.annotations.SerializedName

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
data class SalesResponse(val sales: List<Sale>)

data class Sale(val name: String,
                val sale: String,
                @SerializedName("sale_key") val saleKey: String,
                val store: String,
                @SerializedName("sale_url") val saleUrl: String,
                @SerializedName("image_urls") val imageUrls: ImageUrls,
                val begins: String,
                val ends: String,
                val description: String,
                val size: Int,
                val products: List<String>)

data class ImageUrls(@SerializedName("161x110") val small: List<Image>,
                     @SerializedName("366x186") val medium: List<Image>,
                     @SerializedName("645x295") val big: List<Image>)

data class Image(val url: String, val width: Int, val height: Int)