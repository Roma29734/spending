package com.example.spending.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spending.R
import com.example.spending.databinding.ActivityStartBinding
import com.example.spending.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.MainScope

class StartActivity : DaggerAppCompatActivity() {

    private var _binding: ActivityStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun goToMain() {
        finish()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}