package com.jarka.vpgilt.dagger

import com.jarka.vpgilt.detail.DetailActivity
import com.jarka.vpgilt.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by lukasz.jarka on 03/03/2018.
 */
@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun proviedDetailActivityInjector(): DetailActivity
}