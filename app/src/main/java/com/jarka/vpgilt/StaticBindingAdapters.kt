package com.jarka.vpgilt

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


/**
 * Created by lukasz.jarka on 04/03/2018.
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView).load(url).into(imageView)
}
