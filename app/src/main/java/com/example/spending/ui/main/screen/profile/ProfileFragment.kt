package com.example.spending.ui.main.screen.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.spending.R
import com.example.spending.base.BaseFragment
import com.example.spending.databinding.FragmentProfileBinding
import com.example.spending.ui.main.MainActivity
import com.example.spending.ui.main.viewModel.MainViewModel
import com.example.spending.utils.ThemeState

class ProfileFragment :
    BaseFragment<FragmentProfileBinding>
        (FragmentProfileBinding::inflate) {

    private val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadTheme()

        viewModel.getProfile()

        viewModel.profile.observe(viewLifecycleOwner) {
            binding.apply {
                textName.text = it.name
                textAge.text = it.age.toString()
                upBar.imageButtonBack.setOnClickListener {
                    Navigation.findNavController(view).popBackStack()
                }
            }
        }
        binding.ragioGroop.setOnCheckedChangeListener { _, _ ->
            saveTheme()
            (requireActivity() as MainActivity).setTheme()
        }
    }

    private fun loadTheme() {
        val loadTheme = (requireActivity() as MainActivity).loadData()
        val saveTheme = (requireActivity() as MainActivity)
        val group = binding.ragioGroop
        binding.ragioGroop.apply {
            if (loadTheme == null) {
                saveTheme.saveDate(ThemeState.SYSTEM)
                binding.rBSystem.isChecked = true
            } else {
                when (loadTheme) {
                    "SYSTEM" -> {
                        binding.rBSystem.isChecked = true
                    }
                    "DARK" -> {
                        binding.rBDark.isChecked = true
                    }
                    "WHITE" -> {
                        binding.rBWhile.isChecked = true
                    }
                }
            }
        }

    }

    private fun saveTheme() {
        val saveTheme = (requireActivity() as MainActivity)

        when (binding.ragioGroop.checkedRadioButtonId) {
            R.id.rBDark -> {
                saveTheme.saveDate(ThemeState.DARK)
            }
            R.id.rBSystem -> {
                saveTheme.saveDate(ThemeState.SYSTEM)
            }
            R.id.rBWhile -> {
                saveTheme.saveDate(ThemeState.WHITE)
            }
        }
    }
}