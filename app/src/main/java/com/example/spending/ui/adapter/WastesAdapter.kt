package com.example.spending.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.spending.R
import com.example.spending.data.model.WastesEntity
import com.example.spending.databinding.DetailWastesRowBinding

class WastesAdapter(): RecyclerView.Adapter<WastesAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: DetailWastesRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var model = emptyList<WastesEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DetailWastesRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val positionModel = model[position]

        holder.binding.apply {
            if(positionModel.withdrawal) {
                textWastes.text = "-${positionModel.sum}"
            } else {

                textWastes.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.mainGreen))

                textWastes.text = "+${positionModel.sum}"
            }
            textDate.text = positionModel.data
            textName.text = positionModel.name
        }
    }

    override fun getItemCount(): Int {
        return model.size
    }

    fun setData(list: List<WastesEntity>) {
        model = list
        notifyDataSetChanged()
    }
}