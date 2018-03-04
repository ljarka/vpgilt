package com.jarka.vpgilt.main

import com.jarka.vpgilt.main.network.ActiveSalesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
@Module
class MainViewModelModule {

    @Provides
    fun providesActiveSalesService(retrofit: Retrofit): ActiveSalesService {
        return retrofit.create(ActiveSalesService::class.java)
    }
}