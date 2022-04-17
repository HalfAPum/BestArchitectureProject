package com.example.pagingsample.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.pagingsample.R
import com.example.pagingsample.databinding.CharacterItemLayoutBinding
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.ui.getString
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder

class CharacterItemViewHolder(private val binding: CharacterItemLayoutBinding) :
    BaseItemViewHolder<Character>(binding.root) {

    override fun update(item: Character) {
        with(binding) {
            name.text = item.name
            characterAvatar.load(item.image)
            gender.text = getString(R.string.gender, item.gender)
            status.text = getString(R.string.status, item.status)
        }
    }

    companion object {
        fun create(parent: ViewGroup) = CharacterItemViewHolder(
            CharacterItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}