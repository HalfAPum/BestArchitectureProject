package com.example.pagingsample.ui.fragment.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentCharacterBinding
import com.example.pagingsample.model.Character
import com.example.pagingsample.ui.adapter.CharacterPagingAdapter
import com.example.pagingsample.ui.launchSubscribeFlow
import com.example.pagingsample.ui.navigate
import com.example.pagingsample.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character) {

    private val viewModel : CharacterViewModel by viewModels()

    private val binding: FragmentCharacterBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CharacterPagingAdapter()
        adapter.onItemClick = ::onItemClick
        binding.recyclerView.adapter = adapter

        launchSubscribeFlow {
            viewModel.charactersFlow().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun onItemClick(item: Character) {
        navigate(CharacterFragmentDirections.actionToDetails(item))
    }

}