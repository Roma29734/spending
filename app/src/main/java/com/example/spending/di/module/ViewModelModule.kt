package com.example.spending.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spending.ui.ViewModelFactory
import com.example.spending.ui.main.screen.home.HomeViewModel
import com.example.spending.ui.main.viewModel.MainViewModel
import com.example.spending.ui.start.viewModel.StartViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    abstract fun bindStartViewModel(imagesListViewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(imagesListViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(imagesListViewModel: HomeViewModel): ViewModel
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)