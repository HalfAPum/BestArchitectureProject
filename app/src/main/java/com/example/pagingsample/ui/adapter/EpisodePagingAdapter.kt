package com.example.pagingsample.ui.adapter

import android.view.ViewGroup
import com.example.pagingsample.model.Episode
import com.example.pagingsample.ui.adapter.base.BasePagingAdapter
import com.example.pagingsample.ui.viewholder.EpisodeItemViewHolder

class EpisodePagingAdapter : BasePagingAdapter<Episode>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeItemViewHolder {
        return EpisodeItemViewHolder.create(parent)
    }

}