package com.jarka.vpgilt.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jarka.vpgilt.databinding.ItemSalesBinding
import java.util.*

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
class SalesRecyclerViewAdapter : RecyclerView.Adapter<SalesRecyclerViewAdapter.SalesViewHolder>() {
    var onSaleItemClickListener: OnSaleItemClickListener? = null

    var items: List<SaleUi> = Collections.emptyList()
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        holder.binding.sale = items[position]
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            SalesViewHolder(ItemSalesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    inner class SalesViewHolder(val binding: ItemSalesBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onSaleItemClickListener?.onSaleItemClick(items[adapterPosition])
        }
    }
}