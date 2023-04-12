package com.example.spending.ui.start.screen.createAccount

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.spending.R
import com.example.spending.base.BaseFragment
import com.example.spending.data.model.UserEntity
import com.example.spending.databinding.FragmentCreateAccountBinding
import com.example.spending.ui.start.StartActivity
import com.example.spending.ui.start.viewModel.StartViewModel

class CreateAccountFragment :
    BaseFragment<FragmentCreateAccountBinding>
        (FragmentCreateAccountBinding::inflate) {

    private val viewModel: StartViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.matButtonRightNow.setOnClickListener {
            if(inputCheck(binding.tiEmail.text.toString(), binding.tiPassword.text.toString())) {
                val model = UserEntity(0, binding.tiEmail.text.toString(), binding.tiPassword.text.toString().toInt())
                viewModel.insertUser(model)
                (requireActivity() as StartActivity).goToMain()
            } else {
                Toast.makeText(context, "Заполните поля", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun inputCheck (title: String, subTitle: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(subTitle))
    }
}