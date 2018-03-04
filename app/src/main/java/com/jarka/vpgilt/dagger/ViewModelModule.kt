package com.jarka.vpgilt.dagger

import android.arch.lifecycle.ViewModel
import com.jarka.vpgilt.detail.DetailViewModel
import com.jarka.vpgilt.main.MainViewModel
import com.jarka.vpgilt.main.MainViewModelModule
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

/**
 * Created by lukasz.jarka on 04/03/2018.
 */
@Module(includes = arrayOf(MainViewModelModule::class))
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)