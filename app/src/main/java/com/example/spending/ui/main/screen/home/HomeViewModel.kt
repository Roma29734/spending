package com.example.spending.ui.main.screen.home

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
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
    application: Application
) : AndroidViewModel(application) {

    private val context = application

    private var _homeState = MutableLiveData<ResultHome<List<WastesEntity>>>()
    val homeState get() = _homeState

    fun getState() {
        repository.getSizeWastesTable()
            .subscribeOn(Schedulers.io())
            .subscribeBy(onSuccess = { result ->
                if (result == 0) {
                    _homeState.postValue(ResultHome.Empty())
                    return@subscribeBy
                } else {
                    repository.readWastesTable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(onSuccess = { data ->
                            val money = loadData()
                            var currencyResult = loadDataCurrency()
                            if(currencyResult == null) {
                                saveDateCurrency("$")
                                currencyResult = loadDataCurrency()!!
                            }
                            if(money != null) {
                                _homeState.postValue(ResultHome.Success(data = data.reversed(), totalAmount = money.toInt(), currency = currencyResult))
                            } else {
                                _homeState.postValue(ResultHome.Success(data = data.reversed(), totalAmount = 0, currency = currencyResult))
                            }
                        }, onError = {
                            _homeState.postValue(ResultHome.Error("read"))
                        })
                }
            }, onError = {
                Log.d("HomeViewModel", "${it.message}")
                _homeState.postValue(ResultHome.Error("size"))
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
                val money = loadData()
                if(money != null) {
                    saveDate((loadData()!!.toInt() - sum.toInt()).toString())
                } else {
                    saveDate((0 - sum.toInt()).toString())
                }
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
                val money = loadData()
                if(money != null) {
                    saveDate((loadData()!!.toInt() + sum.toInt()).toString())
                } else {
                    saveDate((0 + sum.toInt()).toString())
                }
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

    private fun saveDate(state: String) {
        val sheared = context.getSharedPreferences("money", AppCompatActivity.MODE_PRIVATE)

        sheared.edit().apply{
            putString("MONEY_KEY", state)
        }.apply()
    }

    private fun loadData(): String? {
        val sheared = context.getSharedPreferences("money", AppCompatActivity.MODE_PRIVATE)

        return sheared.getString("MONEY_KEY", null)
    }

    private fun saveDateCurrency(state: String) {
        val sheared = context.getSharedPreferences("currency", AppCompatActivity.MODE_PRIVATE)

        sheared.edit().apply{
            putString("CURRENCY_KEY", state)
        }.apply()
    }

    private fun loadDataCurrency(): String? {
        val sheared = context.getSharedPreferences("currency", AppCompatActivity.MODE_PRIVATE)

        return sheared.getString("CURRENCY_KEY", null)
    }
}