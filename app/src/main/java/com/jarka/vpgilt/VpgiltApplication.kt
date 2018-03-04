package com.jarka.vpgilt

import com.jarka.vpgilt.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


/**
 * Created by lukasz.jarka on 03/03/2018.
 */
class VpgiltApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
                .builder()
                .application(this)
                .create(this)
    }
}