package com.example.spending.ui.main.screen.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.spending.R
import com.example.spending.base.BaseFragment
import com.example.spending.databinding.FragmentProfileBinding
import com.example.spending.ui.main.viewModel.MainViewModel

class ProfileFragment :
    BaseFragment<FragmentProfileBinding>
        (FragmentProfileBinding::inflate) {

    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfile()
        viewModel.profile.observe(viewLifecycleOwner) {

        }
    }
}