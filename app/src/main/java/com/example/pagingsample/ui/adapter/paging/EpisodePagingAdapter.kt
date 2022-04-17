package com.example.pagingsample.ui.adapter.paging

import android.view.ViewGroup
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.ui.adapter.paging.base.BasePagingAdapter
import com.example.pagingsample.ui.viewholder.EpisodeItemViewHolder

class EpisodePagingAdapter : BasePagingAdapter<Episode>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeItemViewHolder {
        return EpisodeItemViewHolder.create(parent)
    }

}