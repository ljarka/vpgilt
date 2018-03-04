package com.jarka.vpgilt.detail

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.jarka.vpgilt.R
import com.jarka.vpgilt.dagger.DaggerViewModelFactory
import com.jarka.vpgilt.main.SaleUi
import com.jarka.vpgilt.showChild
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject


/**
 * Created by lukasz.jarka on 03/03/2018.
 */
class DetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var detailViewModel: DetailViewModel

    private var productsDataDisposable: Disposable = Disposables.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        detailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DetailViewModel::class.java)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val productsAdapter = ProductsRecyclerViewAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.adapter = productsAdapter
        val saleItem: SaleUi = intent.getParcelableExtra(SALE_ITEM)
        name.text = saleItem.name
        description.text = saleItem.description


        productsDataDisposable = detailViewModel.loadProductsData(saleItem.products)
                .subscribe({
                    when (it) {
                        is Success -> {
                            productsAdapter.items = it.results
                            viewAnimator.showChild(if (productsAdapter.items.isEmpty()) emptyView else recyclerView)
                        }
                        is Error -> {
                            viewAnimator.showChild(error)
                            Log.e(TAG, "", it.error)
                        }
                        is InProgress -> {
                            viewAnimator.showChild(progressBar)
                        }
                    }
                })
        Glide.with(expandedImage).load(saleItem.image).into(expandedImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        productsDataDisposable.dispose()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TAG = "DetailActivity"
        private const val SALE_ITEM = "sale_item"

        fun createIntent(context: Context, saleItem: SaleUi): Intent {
            return Intent(context, DetailActivity::class.java)
                    .putExtra(SALE_ITEM, saleItem)
        }
    }
}
