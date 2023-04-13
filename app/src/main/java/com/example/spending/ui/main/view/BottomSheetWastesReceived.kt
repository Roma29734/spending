package com.example.spending.ui.main.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.spending.databinding.BottomSheetWastesReceivedBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetWastesReceived constructor(
    var callBackDel: ((nameCat: String, sum: String) -> Unit)? = null
): BottomSheetDialogFragment() {

    private var _binding: BottomSheetWastesReceivedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomSheetWastesReceivedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.matButtonAdd.setOnClickListener {
            if(inputCheck(binding.tiEmail.text.toString(), binding.tiPassword.text.toString())) {
                callBackDel?.let { it1 -> it1(binding.tiEmail.text.toString(), binding.tiPassword.text.toString()) }
                this@BottomSheetWastesReceived.dismiss()
            } else {
                Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun inputCheck (title: String, subTitle: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(subTitle))
    }
}