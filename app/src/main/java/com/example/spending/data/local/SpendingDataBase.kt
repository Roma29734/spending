package com.example.spending.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spending.data.model.UserEntity
import com.example.spending.data.model.WastesEntity

@Database(
    entities = [
        UserEntity::class,
        WastesEntity::class
    ],
    version = 1,
    exportSchema = false,
)
abstract class SpendingDataBase : RoomDatabase() {
    abstract fun spendingDao(): SpendingDao
}