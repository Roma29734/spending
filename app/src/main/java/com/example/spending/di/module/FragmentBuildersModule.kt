package com.example.spending.di.module

import com.example.spending.ui.main.screen.home.HomeFragment
import com.example.spending.ui.main.screen.profile.ProfileFragment
import com.example.spending.ui.start.screen.createAccount.CreateAccountFragment
import com.example.spending.ui.start.screen.hello.HelloFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHelloFragment(): HelloFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateAccountFragment(): CreateAccountFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment
}