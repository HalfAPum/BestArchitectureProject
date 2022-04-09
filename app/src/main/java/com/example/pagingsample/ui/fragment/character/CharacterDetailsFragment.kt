package com.example.pagingsample.ui.fragment.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentCharacterDetailsBinding
import com.example.pagingsample.model.Character

class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    private val binding: FragmentCharacterDetailsBinding by viewBinding()

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character = args.character
        setUpUI(character)
    }

    private fun setUpUI(character: Character) {
        with(binding) {
//            characterImage.load(character.image)
        }
    }

}