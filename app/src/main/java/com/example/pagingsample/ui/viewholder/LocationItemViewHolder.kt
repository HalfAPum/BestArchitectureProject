package com.example.pagingsample.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.pagingsample.databinding.ListItemLayoutBinding
import com.example.pagingsample.model.Location
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder

class LocationItemViewHolder(private val binding: ListItemLayoutBinding) :
    BaseItemViewHolder<Location>(binding.root) {

    override fun update(item: Location) {
        binding.name.text = item.name
    }

    companion object {

        fun create(parent: ViewGroup) = LocationItemViewHolder(
            ListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}