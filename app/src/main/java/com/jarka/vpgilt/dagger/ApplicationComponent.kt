package com.jarka.vpgilt.dagger

import android.app.Application
import android.content.Context
import com.jarka.vpgilt.VpgiltApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by lukasz.jarka on 03/03/2018.
 */
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, ActivityBuildersModule::class, NetworkModule::class, ViewModelModule::class))
@Singleton
interface ApplicationComponent : AndroidInjector<VpgiltApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<VpgiltApplication>() {
        @BindsInstance
        abstract fun application(context: Context): Builder
    }

    fun inject(app: Application)
}