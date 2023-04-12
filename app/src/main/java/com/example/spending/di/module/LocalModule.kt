package com.example.spending.di.module

import android.app.Application
import androidx.room.Room
import com.example.spending.data.local.SpendingDao
import com.example.spending.data.local.SpendingDataBase
import com.example.spending.data.repository.SpendingRepository
import com.example.spending.data.repository.SpendingRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    fun provideSpendingRepository(impl: SpendingRepositoryImpl): SpendingRepository = impl

    @Provides
    fun provideSpendingRepositoryIpl(
        dao: SpendingDao,
    ): SpendingRepositoryImpl = SpendingRepositoryImpl(dao)

    @Provides
    fun provideSpendingDao(spendingDataBase: SpendingDataBase): SpendingDao =
        spendingDataBase.spendingDao()

    @Provides
    @Singleton
    fun provideSpendingDataBase(context: Application): SpendingDataBase =
        Room.databaseBuilder(
            context,
            SpendingDataBase::class.java,
            "spending_base"
        ).build()
}
