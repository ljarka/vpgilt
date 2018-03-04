package com.jarka.vpgilt.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jarka.vpgilt.R
import java.util.*

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
class ProductsRecyclerViewAdapter : RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductsViewHolder>() {

    var items: List<ProductUi> = Collections.emptyList()
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        Glide.with(holder.itemView).load(items[position].imageUrl).into(holder.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductsViewHolder(itemView)
    }

    override fun getItemCount() = items.size


    inner class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.image)
    }

}