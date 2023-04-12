package com.example.spending.ui.start.screen.hello

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.spending.R
import com.example.spending.base.BaseFragment
import com.example.spending.databinding.FragmentHelloBinding


class HelloFragment :
    BaseFragment<FragmentHelloBinding>
        (FragmentHelloBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.matButtonRightNow.setOnClickListener {
            val action = HelloFragmentDirections.actionHelloFragmentToCreateAccountFragment()
            Navigation.findNavController(view).navigate(action)
        }

    }
}