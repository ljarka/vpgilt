package com.jarka.vpgilt.main

import android.arch.lifecycle.ViewModel
import com.jarka.vpgilt.main.network.ActiveSalesService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
class MainViewModel @Inject constructor(private val activeSales: ActiveSalesService) : ViewModel() {

    private val activeSalesSubject = BehaviorSubject.create<ActiveSalesResultUi>()

    private val activeSalesOfWomenCategory: Observable<ActiveSalesResultUi> by lazy {
        activeSales.getSalesOfWomenCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { Observable.fromIterable(it.sales) }
                .map { SaleUi(it.name, it.imageUrls.big.first().url, it.saleKey, it.products, it.description) }
                .take(10)
                .toList()
                .toObservable()
                .map<ActiveSalesResultUi> { Success(it) }
                .onErrorReturn { Error(it) }
                .startWith(InProgress)
    }

    fun getActiveSales(): Observable<ActiveSalesResultUi> {
        return if (activeSalesSubject.hasValue() && activeSalesSubject.value is Success) {
            return Observable.just(activeSalesSubject.value)
        } else if (activeSalesSubject.value is InProgress) {
            activeSalesSubject
        } else {
            activeSalesOfWomenCategory.subscribe({
                activeSalesSubject.onNext(it)
            })
            activeSalesSubject
        }
    }
}