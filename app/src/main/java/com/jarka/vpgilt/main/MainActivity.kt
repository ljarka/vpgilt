package com.jarka.vpgilt.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.jarka.vpgilt.R
import com.jarka.vpgilt.dagger.DaggerViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by lukasz.jarka on 03/03/2018.
 */

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

    }
}
