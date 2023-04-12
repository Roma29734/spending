package com.example.spending.data.repository

import com.example.spending.data.local.SpendingDao
import com.example.spending.data.model.UserEntity
import com.example.spending.data.model.WastesEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SpendingRepositoryImpl @Inject constructor(
    private val dao: SpendingDao,
): SpendingRepository {
    override fun insertWastesTable(wastesEntity: WastesEntity) {
        dao.insertWastesTable(wastesEntity)
    }

    override fun readWastesTable(): Single<List<WastesEntity>> {
        return dao.readWastesTable()
    }

    override fun getSizeWastesTable(): Single<Int> {
        return dao.getSizeWastesTable()
    }

    override fun insertUserTable(userEntity: UserEntity) {
        dao.insertUserTable(userEntity)
    }

    override fun readUserTable(): Single<List<UserEntity>> {
        return dao.readUserTable()
    }

    override fun getSizeUserTable(): Single<Int> {
        return dao.getSizeUserTable()
    }
}