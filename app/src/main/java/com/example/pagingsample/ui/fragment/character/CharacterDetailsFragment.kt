package com.example.pagingsample.ui.fragment.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentCharacterDetailsBinding
import com.example.pagingsample.model.character.CharacterWithDetails
import com.example.pagingsample.ui.navigate
import com.example.pagingsample.viewmodel.CharacterDetailsViewModel
import com.example.pagingsample.viewmodel.DetailsState
import com.example.pagingsample.viewmodel.DetailsUiAction
import com.example.pagingsample.viewmodel.NavigationSideEffect
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    private val binding: FragmentCharacterDetailsBinding by viewBinding()

    private val viewModel: CharacterDetailsViewModel by viewModels()

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::sideEffect)
        viewModel.postAction(DetailsUiAction.Update(args.characterId))
    }

    private fun render(state: DetailsState) {
        if (state is DetailsState.Data<*>) {
            binding.bindUi(state.item as CharacterWithDetails)
        }
    }

    private fun FragmentCharacterDetailsBinding.bindUi(item: CharacterWithDetails) {
        characterImage.load(item.character.image)
        name.text = item.character.name
        created.text = item.details.created
        gender.text = getString(R.string.gender, item.character.gender)
        statusButton.text = getString(R.string.status, item.character.status)
        statusButton.setOnClickListener {
            item.details.lastLocationId?.let {
                viewModel.postAction(DetailsUiAction.Navigate(it))
            }
        }
    }

    private fun sideEffect(sideEffect: NavigationSideEffect) {
        navigate(CharacterDetailsFragmentDirections
            .actionCharacterDetailsFragmentToLocationDetailsFragment(
                sideEffect.id
            )
        )
    }

}