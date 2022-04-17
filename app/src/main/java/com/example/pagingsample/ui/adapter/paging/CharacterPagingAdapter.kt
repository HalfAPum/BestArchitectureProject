package com.example.pagingsample.ui.adapter.paging

import android.view.ViewGroup
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.ui.adapter.paging.base.BasePagingAdapter
import com.example.pagingsample.ui.viewholder.CharacterItemViewHolder

class CharacterPagingAdapter : BasePagingAdapter<Character>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        return CharacterItemViewHolder.create(parent)
    }

}