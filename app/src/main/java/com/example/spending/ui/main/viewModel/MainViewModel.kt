package com.example.spending.ui.main.viewModel

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spending.data.model.UserEntity
import com.example.spending.data.repository.SpendingRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: SpendingRepository,
    application: Application
): AndroidViewModel(application) {

    private val context = application

    private var _stateCreateTable: MutableLiveData<Boolean> = MutableLiveData()
    val stateCreateTable get() = _stateCreateTable

    fun getSizeTable() {
        repository.getSizeUserTable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (onSuccess = { result ->
                _stateCreateTable.postValue(result != 0)
            }, onError = {

            })
    }

    private var _profile: MutableLiveData<UserEntity> = MutableLiveData()
    val profile get() = _profile

    fun getProfile() {
        repository.readUserTable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (onSuccess = {
                _profile.postValue(it[0])
            }, onError = {

            })
    }


    fun saveDate(state: String) {
        val sheared = context.getSharedPreferences("currency", AppCompatActivity.MODE_PRIVATE)

        sheared.edit().apply{
            putString("CURRENCY_KEY", state)
        }.apply()
    }

    fun loadData(): String? {
        val sheared = context.getSharedPreferences("currency", AppCompatActivity.MODE_PRIVATE)

        return sheared.getString("CURRENCY_KEY", null)
    }
}