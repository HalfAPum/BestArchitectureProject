package com.example.pagingsample.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.pagingsample.databinding.EpisodeItemLayoutBinding
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder

class EpisodeItemViewHolder(private val binding: EpisodeItemLayoutBinding) :
    BaseItemViewHolder<Episode>(binding.root) {

    override fun update(item: Episode) {
        binding.name.text = item.name
        binding.airDate.text = item.airDate
    }

    companion object {

        fun create(parent: ViewGroup) = EpisodeItemViewHolder(
            EpisodeItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}