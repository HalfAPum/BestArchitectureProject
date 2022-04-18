package com.example.pagingsample.ui.fragment.character

import com.example.pagingsample.model.character.Character
import com.example.pagingsample.ui.adapter.paging.CharacterPagingAdapter
import com.example.pagingsample.ui.fragment.base.PagingFragment
import com.example.pagingsample.viewmodel.CharacterPaging1ViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber

class CharacterFragment : PagingFragment<Character>() {

    override val adapter by lazy { CharacterPagingAdapter() }

    val viewModel
        get() = getViewModel<CharacterPaging1ViewModel>()

    override fun onStart() {
        super.onStart()
        Timber.d("tag1 ${viewModel.itemRepository}")
    }

    override fun getDetailsNavigationDirection(id: Long) =
        CharacterFragmentDirections.actionToDetails(id)

}