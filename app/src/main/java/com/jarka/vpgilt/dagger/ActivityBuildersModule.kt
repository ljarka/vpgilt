package com.jarka.vpgilt.dagger

import com.jarka.vpgilt.main.MainActivity
import com.jarka.vpgilt.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by lukasz.jarka on 03/03/2018.
 */

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun provideMainActivityInjector(): MainActivity
}