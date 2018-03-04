package com.jarka.vpgilt.main

import android.arch.lifecycle.ViewModel
import com.jarka.vpgilt.main.network.ActiveSalesService
import javax.inject.Inject

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
class MainViewModel @Inject constructor(val activeSales: ActiveSalesService) : ViewModel() {
}