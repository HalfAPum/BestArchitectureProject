package com.example.pagingsample.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pagingsample.R
import com.example.pagingsample.databinding.PassengerItemLayoutBinding
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.model.local.character.Character
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder

class CharacterItemViewHolder(private val binding: PassengerItemLayoutBinding) :
    BaseItemViewHolder<Character>(binding.root) {

    override fun update(item: Character) {
        binding.passengerName.text = item.name
    }

    companion object {

        fun create(parent: ViewGroup) = CharacterItemViewHolder(
            PassengerItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}