package com.example.spending.ui.main.screen.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spending.data.model.WastesEntity
import com.example.spending.data.repository.SpendingRepository
import com.example.spending.data.repository.SpendingRepositoryImpl
import com.example.spending.utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
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
}