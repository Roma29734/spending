package com.example.spending.ui.main.screen.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.spending.R
import com.example.spending.base.BaseFragment
import com.example.spending.databinding.FragmentHomeBinding
import com.example.spending.ui.adapter.WastesAdapter
import com.example.spending.ui.main.MainActivity
import com.example.spending.ui.main.view.BottomSheetWastesReceived

class HomeFragment :
    BaseFragment<FragmentHomeBinding>
        (FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private val adapter = WastesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSizeTable()
        viewModel.stateCreateTable.observe(viewLifecycleOwner) {
            if(it != true) {
                (requireActivity() as MainActivity).goToStart()
            }
        }

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
                    is ResultHome.Success -> {
                        state.data?.let { adapter.setData(it) }
                        widget.textBalance.text = "${state.totalAmount.toString()} ${state.currency}"
                        textEmpty.isVisible = false
                    }
                    is ResultHome.Empty -> {
                        textEmpty.isVisible = true
                    }
                    is ResultHome.Error -> {
                        Toast.makeText(context, "Error ${state.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            buttonWidget.matButtonSpent.setOnClickListener {
                val bottomSheet = BottomSheetWastesReceived(viewModel::insertWastesTableSpent)
                bottomSheet.show(childFragmentManager, "aey")
            }
            buttonWidget.matButtonReceived.setOnClickListener {
                val bottomSheet = BottomSheetWastesReceived(viewModel::insertWastesTableReceived)
                bottomSheet.show(childFragmentManager, "aey")
            }
        }
    }
}