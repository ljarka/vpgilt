package com.jarka.vpgilt.detail

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
sealed class ProductsResultUi

object InProgress : ProductsResultUi()
data class Success(val results: List<ProductUi>) : ProductsResultUi()
data class Error(val error: Throwable) : ProductsResultUi()