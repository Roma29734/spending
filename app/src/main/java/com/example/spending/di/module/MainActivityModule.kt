package com.example.spending.di.module

import com.example.spending.ui.main.MainActivity
import com.example.spending.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentBuildersModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}