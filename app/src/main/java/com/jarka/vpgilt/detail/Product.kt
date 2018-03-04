package com.jarka.vpgilt.detail

import com.google.gson.annotations.SerializedName

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
data class Product(val name: String, val id: String, @SerializedName("image_urls") val images: ImageUrls)

data class ImageUrls(@SerializedName("300x400") val image: List<Image>)

data class Image(val url: String)