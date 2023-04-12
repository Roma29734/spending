package com.example.spending.ui.main.screen.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.spending.R
import com.example.spending.base.BaseFragment
import com.example.spending.databinding.FragmentHomeBinding
import com.example.spending.ui.adapter.WastesAdapter
import com.example.spending.utils.Resource

class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private val adapter = WastesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            upBar.profile.setOnClickListener{
                val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
                Navigation.findNavController(view).navigate(action)
            }
            recyclerView.adapter = adapter
            upBar.textView3.text = getString(R.string.app_name)
            viewModel.getState()
            viewModel.homeState.observe(viewLifecycleOwner) { state ->
                when(state) {
                    is Resource.Success -> {
                        state.data?.let { adapter.setData(it) }
                        textEmpty.isVisible = false
                    }
                    is Resource.Empty -> {
                        textEmpty.isVisible = true
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, "Error ${state.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}