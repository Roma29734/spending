package com.example.spending.data.repository

import com.example.spending.data.model.UserEntity
import com.example.spending.data.model.WastesEntity
import io.reactivex.rxjava3.core.Single

interface SpendingRepository {

    fun insertWastesTable(wastesEntity: WastesEntity)

    fun readWastesTable(): Single<List<WastesEntity>>

    fun getSizeWastesTable(): Single<Int>


    fun insertUserTable(userEntity: UserEntity)

    fun readUserTable(): Single<List<UserEntity>>

    fun getSizeUserTable(): Single<Int>
}