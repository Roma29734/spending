package com.example.spending.ui.start.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spending.data.model.UserEntity
import com.example.spending.data.repository.SpendingRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val repository: SpendingRepository,
) : ViewModel() {

    fun insertUser(user: UserEntity) {

        Observable.just(user)
            .subscribeOn(Schedulers.io())
            .subscribeBy { result ->
                repository.insertUserTable(result)
            }
    }
}