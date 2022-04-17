package com.example.pagingsample.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.pagingsample.databinding.GridCharacterImageItemLayoutBinding
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder

class CharacterImageViewHolder(
    private val binding: GridCharacterImageItemLayoutBinding
) : BaseItemViewHolder<Character>(binding.root) {

    override fun update(item: Character) {
        with(binding.root) {
            post {
                binding.characterImage.load(item.image) {
                    size(width)
                }
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup) = CharacterImageViewHolder(
            GridCharacterImageItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}