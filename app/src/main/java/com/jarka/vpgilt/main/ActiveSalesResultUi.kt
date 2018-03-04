package com.jarka.vpgilt.main

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
sealed class ActiveSalesResultUi

object InProgress : ActiveSalesResultUi()
data class Success(val result: List<SaleUi>) : ActiveSalesResultUi()
data class Error(val error: Throwable) : ActiveSalesResultUi()

@SuppressLint("ParcelCreator")
@Parcelize
data class SaleUi(val name: String, val image: String, val saleKey: String,
                  val products: List<String>?, val description: String) : Parcelable