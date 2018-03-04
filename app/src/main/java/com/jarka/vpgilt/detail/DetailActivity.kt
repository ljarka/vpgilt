package com.jarka.vpgilt.detail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.jarka.vpgilt.R
import android.support.v4.app.NavUtils


/**
 * Created by lukasz.jarka on 03/03/2018.
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        private const val SALE_KEY = "sale_key"
        private const val IMAGE_URL = "image_url"

        fun createIntent(context: Context, imageUrl: String, saleKey: String): Intent {
            return Intent(context, DetailActivity::class.java)
                    .putExtra(SALE_KEY, saleKey)
                    .putExtra(IMAGE_URL, imageUrl)
        }
    }
}
