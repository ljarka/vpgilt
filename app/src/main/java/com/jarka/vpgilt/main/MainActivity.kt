package com.jarka.vpgilt.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.VERTICAL
import android.util.Log
import com.jarka.vpgilt.R
import com.jarka.vpgilt.dagger.DaggerViewModelFactory
import com.jarka.vpgilt.detail.DetailActivity
import com.jarka.vpgilt.showChild
import kotlinx.android.synthetic.main.activity_detail.viewAnimator
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by lukasz.jarka on 03/03/2018.
 */
class MainActivity : DaggerAppCompatActivity(), OnSaleItemClickListener {
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var mainViewModel: MainViewModel
    private var activeSalesDisposable: Disposable = Disposables.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        val adapter = SalesRecyclerViewAdapter()
        adapter.onSaleItemClickListener = this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)

        activeSalesDisposable = mainViewModel.getActiveSales()
                .subscribe({
                    when (it) {
                        is InProgress -> {
                            viewAnimator.showChild(progressBar)
                        }
                        is Success -> {
                            viewAnimator.showChild(recyclerView)
                            adapter.items = it.result
                        }
                        is Error -> {
                            viewAnimator.showChild(error)
                            Log.e(MainActivity.TAG, "", it.error)
                        }
                    }
                })

    }

    override fun onDestroy() {
        super.onDestroy()
        activeSalesDisposable.dispose()
    }

    override fun onSaleItemClick(saleUi: SaleUi) {
        startActivity(DetailActivity.createIntent(this, saleUi))
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
