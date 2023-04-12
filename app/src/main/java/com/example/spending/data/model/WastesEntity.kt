package com.example.spending.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wastes_table")
data class WastesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "sum")val sum: Int,
    @ColumnInfo(name = "data")val data: String,
)