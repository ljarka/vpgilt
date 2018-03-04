package com.jarka.vpgilt.main

/**
 * Created by lukasz.jarka on 04/03/2018.
 */

sealed class ActiveSalesResultUi

object InProgress : ActiveSalesResultUi()
data class Success(val result: List<SaleUi>) : ActiveSalesResultUi()
data class Error(val error: Throwable) : ActiveSalesResultUi()