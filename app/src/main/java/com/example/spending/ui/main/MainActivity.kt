package com.example.spending.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.spending.R
import com.example.spending.databinding.ActivityMainBinding
import com.example.spending.databinding.ActivityStartBinding
import com.example.spending.ui.main.viewModel.MainViewModel
import com.example.spending.ui.start.StartActivity
import com.example.spending.utils.ThemeState
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun goToStart() {
        finish()
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }

    fun saveDate(state: ThemeState) {
        val sheared = getSharedPreferences("stateTheme", MODE_PRIVATE)

        sheared.edit().apply{
            putString("STATE_KEY", state.toString())
        }.apply()
    }

    fun loadData(): String? {
        val sheared = getSharedPreferences("stateTheme", MODE_PRIVATE)

        return sheared.getString("STATE_KEY", null)
    }

    fun setTheme () {
        when(loadData()) {
            null -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
            "SYSTEM" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
            "DARK" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            "WHITE" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setTheme()
    }
}