package com.example.pagingsample.ui.adapter

import android.view.ViewGroup
import com.example.pagingsample.model.Character
import com.example.pagingsample.ui.adapter.base.BasePagingAdapter
import com.example.pagingsample.ui.viewholder.CharacterItemViewHolder

class CharacterPagingAdapter : BasePagingAdapter<Character>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        return CharacterItemViewHolder.create(parent)
    }

}