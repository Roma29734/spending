package com.example.spending.di

import android.app.Application
import com.example.spending.app.App
import com.example.spending.di.module.AppModule
import com.example.spending.di.module.MainActivityModule
import com.example.spending.di.module.StartActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        StartActivityModule::class,
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}