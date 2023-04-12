package com.example.spending.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spending.R
import com.example.spending.databinding.ActivityMainBinding
import com.example.spending.databinding.ActivityStartBinding
import com.example.spending.ui.start.StartActivity
import dagger.android.support.DaggerAppCompatActivity

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
}