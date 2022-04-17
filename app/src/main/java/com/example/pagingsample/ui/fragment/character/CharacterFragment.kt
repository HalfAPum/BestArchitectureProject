package com.example.pagingsample.ui.fragment.character

import androidx.fragment.app.viewModels
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.ui.adapter.paging.CharacterPagingAdapter
import com.example.pagingsample.ui.fragment.base.PagingFragment
import com.example.pagingsample.viewmodel.CharacterPagingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : PagingFragment<Character>() {

    override val adapter by lazy { CharacterPagingAdapter() }

    override val viewModel: CharacterPagingViewModel by viewModels()

    override fun getDetailsNavigationDirection(id: String) =
        CharacterFragmentDirections.actionToDetails(id)

}