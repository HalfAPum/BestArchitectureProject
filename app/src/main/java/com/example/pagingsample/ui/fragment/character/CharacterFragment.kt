package com.example.pagingsample.ui.fragment.character

import com.example.pagingsample.model.Character
import com.example.pagingsample.ui.adapter.CharacterPagingAdapter
import com.example.pagingsample.ui.fragment.base.PagingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : PagingFragment<Character>() {

    override val adapter by lazy { CharacterPagingAdapter() }

    override fun getDetailsNavigationDirection(item: Character) =
        CharacterFragmentDirections.actionToDetails(item)

}