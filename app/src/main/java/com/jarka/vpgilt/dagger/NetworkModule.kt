package com.jarka.vpgilt.dagger

import com.jarka.vpgilt.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


/**
 * Created by lukasz.jarka on 03/03/2018.
 */

@Module
class NetworkModule {

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesOkHttp(@Named("apiKeyInterceptor") interceptor: Interceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

    @Provides
    @Named("apiKeyInterceptor")
    fun providesApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val url = chain.request().url().newBuilder().addQueryParameter("apiKey", BuildConfig.API_KEY).build()
            chain.proceed(chain.request().newBuilder().url(url).build())
        }
    }

    companion object {
        const val API_URL = "https://api.gilt.com/v1/"
    }
}