package com.example.pagingsample.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.ui.diffutil.CharacterImageDiffUtil
import com.example.pagingsample.ui.viewholder.CharacterImageViewHolder
import com.example.pagingsample.utils.TypedVoidCallback

class CharacterImageAdapter : RecyclerView.Adapter<CharacterImageViewHolder>() {

    private val dataList: MutableList<Character> = mutableListOf()

    var onItemClick: TypedVoidCallback<Character> = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterImageViewHolder.create(parent)

    override fun onBindViewHolder(holder: CharacterImageViewHolder, position: Int) {
        dataList[position].let {
            holder.update(it)
            holder.setOnClickListener {
                onItemClick.invoke(it)
            }
        }
    }

    override fun getItemCount() = dataList.size

    fun update(newData: List<Character>) {
        val diffCallback = CharacterImageDiffUtil(dataList, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        dataList.clear()
        dataList.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }
}