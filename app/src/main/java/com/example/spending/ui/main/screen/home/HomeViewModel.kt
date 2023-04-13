package com.example.spending.ui.main.screen.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spending.data.model.WastesEntity
import com.example.spending.data.repository.SpendingRepository
import com.example.spending.data.repository.SpendingRepositoryImpl
import com.example.spending.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: SpendingRepository,
) : ViewModel() {

    private var _homeState = MutableLiveData<Resource<List<WastesEntity>>>()
    val homeState get() = _homeState

    fun getState() {
        repository.getSizeWastesTable()
            .subscribeOn(Schedulers.io())
            .subscribeBy(onSuccess = { result ->
                if (result == 0) {
                    _homeState.postValue(Resource.Empty())
                    return@subscribeBy
                } else {
                    repository.readWastesTable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(onSuccess = { data ->
                            _homeState.postValue(Resource.Success(data = data))
                        }, onError = {
                            _homeState.postValue(Resource.Error("read"))
                        })
                }
            }, onError = {
                Log.d("HomeViewModel", "${it.message}")
                _homeState.postValue(Resource.Error("size"))
            })
    }


    private var _stateCreateTable: MutableLiveData<Boolean> = MutableLiveData()
    val stateCreateTable get() = _stateCreateTable

    fun getSizeTable() {
        repository.getSizeUserTable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { result ->
                _stateCreateTable.postValue(result != 0)
            }, onError = {

            })
    }

    fun insertWastesTableSpent(nameCat: String, sum: String) {
        val model =
            WastesEntity(0, name = nameCat, sum = sum.toInt(), data = getTime(), withdrawal = true)

        Observable.just(model)
            .subscribeOn(Schedulers.io())
            .subscribeBy(onNext = {
                repository.insertWastesTable(it)
                getState()
            }, onError = {
                Log.d("HomeViewModel", "onError ${it.message} ")
            })
    }

    fun insertWastesTableReceived(nameCat: String, sum: String) {
        val model =
            WastesEntity(0, name = nameCat, sum = sum.toInt(), data = getTime(), withdrawal = false)

        Observable.just(model)
            .subscribeOn(Schedulers.io())
            .subscribeBy(onNext = {
                repository.insertWastesTable(it)
                getState()
            }, onError = {
                Log.d("HomeViewModel", "onError ${it.message} ")
            })
    }

    private fun getTime(): String {
        val dateFormat = SimpleDateFormat("d MMM  HH:mm", Locale.getDefault())
        val data = dateFormat.format(Date())
        Log.d("aboba", data)
        return data
    }
}