package com.example.pagingsample.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsample.R
import com.example.pagingsample.databinding.PassengerItemLayoutBinding
import com.example.pagingsample.model.api.PassengerNetwork
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder

class PassengerItemViewHolder(private val binding: PassengerItemLayoutBinding) :
    BaseItemViewHolder<Passenger>(binding.root) {

    override fun update(item: Passenger) {
        binding.passengerName.text = item.name
    }

    companion object {

        fun create(parent: ViewGroup) = PassengerItemViewHolder(
            PassengerItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}