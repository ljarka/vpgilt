package com.jarka.vpgilt.main.network

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by lukasz.jarka on 04/03/2018.
 */

interface ActiveSalesService {

    @GET("women/active.json")
    fun getSalesOfWomenCategory(): Observable<SalesResponse>
}