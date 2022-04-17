package com.example.pagingsample.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.pagingsample.model.character.Character

class CharacterImageDiffUtil(
    private val oldList: List<Character>,
    private val newList: List<Character>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].image == newList[newItemPosition].image
    }

}