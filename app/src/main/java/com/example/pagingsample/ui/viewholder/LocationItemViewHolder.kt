package com.example.pagingsample.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.pagingsample.databinding.LocationItemLayoutBinding
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder

class LocationItemViewHolder(private val binding: LocationItemLayoutBinding) :
    BaseItemViewHolder<Location>(binding.root) {

    override fun update(item: Location) {
        binding.name.text = item.name
    }

    companion object {

        fun create(parent: ViewGroup) = LocationItemViewHolder(
            LocationItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}