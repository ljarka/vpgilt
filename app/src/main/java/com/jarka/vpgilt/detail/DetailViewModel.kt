package com.jarka.vpgilt.detail

import android.arch.lifecycle.ViewModel
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*
import javax.inject.Inject

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
class DetailViewModel @Inject constructor(private val okHttpClient: OkHttpClient) : ViewModel() {

    private val productsDataSubject = BehaviorSubject.create<ProductsResultUi>()

    fun loadProductsData(productsUris: List<String>?): Observable<ProductsResultUi> {

        return if (productsDataSubject.hasValue() && productsDataSubject.value is Success) {
            return Observable.just(productsDataSubject.value)
        } else if (productsDataSubject.value is InProgress) {
            productsDataSubject
        } else {
            productsDataSubject.onNext(InProgress)
            downloadProducsData(productsUris)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        productsDataSubject.onNext(Success(it))
                    }, {
                        productsDataSubject.onNext(Error(it))
                    })
            return productsDataSubject
        }
    }

    private fun downloadProducsData(productsUris: List<String>?): Single<List<ProductUi>> {
        return Observable.fromIterable(productsUris ?: Collections.emptyList())
                .take(10)
                .flatMap { downloadSingleProductInfo(it) }
                .filter({ it.isNotEmpty() })
                .map { Gson().fromJson(it, Product::class.java) }
                .map { ProductUi(it.name, it.images.image.first().url) }
                .toList()
    }

    private fun downloadSingleProductInfo(url: String): Observable<String> {
        return Observable.just(url)
                .map {
                    okHttpClient
                            .newCall(Request.Builder().url(it).build())
                            .execute()
                            .use { it.body()?.string().orEmpty() }
                }.onErrorResumeNext(Observable.just(""))
                .subscribeOn(Schedulers.io())
    }
}
