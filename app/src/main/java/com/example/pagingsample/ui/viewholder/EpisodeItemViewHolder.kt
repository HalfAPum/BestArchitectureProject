package com.example.pagingsample.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.pagingsample.databinding.ListItemLayoutBinding
import com.example.pagingsample.model.Episode
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder

class EpisodeItemViewHolder(private val binding: ListItemLayoutBinding) :
    BaseItemViewHolder<Episode>(binding.root) {

    override fun update(item: Episode) {
        binding.name.text = item.name
    }

    companion object {

        fun create(parent: ViewGroup) = EpisodeItemViewHolder(
            ListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}