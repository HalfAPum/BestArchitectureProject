package com.example.pagingsample.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.pagingsample.model.api.Passenger

class PassengerDiffUtil: DiffUtil.ItemCallback<Passenger>() {

    override fun areItemsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Passenger, newItem: Passenger): Boolean {
        return oldItem == newItem
    }

}