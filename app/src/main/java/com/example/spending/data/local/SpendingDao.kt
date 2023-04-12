package com.example.spending.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spending.data.model.UserEntity
import com.example.spending.data.model.WastesEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface SpendingDao {


    @Insert(entity = WastesEntity::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertWastesTable(wastesEntity: WastesEntity)

    @Query("SELECT * FROM wastes_table")
    fun readWastesTable(): Single<List<WastesEntity>>

    @Query("SELECT COUNT(*) FROM wastes_table")
    fun getSizeWastesTable(): Single<Int>

//    user
    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.IGNORE)
    fun insertUserTable(userEntity: UserEntity)

    @Query("SELECT * FROM user_table")
    fun readUserTable(): Single<List<UserEntity>>

    @Query("SELECT COUNT(*) FROM user_table")
    fun getSizeUserTable(): Single<Int>
}

//
//@Insert(entity = CategoriesEntity::class, onConflict = OnConflictStrategy.IGNORE)
//fun insertCategories(categoriesEntity: CategoriesEntity)
//
//@Query("SELECT * FROM categories_table")
//fun readCategories(): Flowable<List<CategoriesEntity>>
//
//@Query("SELECT COUNT(*) FROM categories_table")
//fun getSizeCategoriesTable(): Single<Int>