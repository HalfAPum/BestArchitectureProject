package com.example.pagingsample.ui.fragment

import androidx.navigation.NavDirections
import com.example.pagingsample.model.Episode
import com.example.pagingsample.ui.adapter.EpisodePagingAdapter
import com.example.pagingsample.ui.fragment.base.PagingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : PagingFragment<Episode>() {

    override val adapter by lazy { EpisodePagingAdapter() }

    override fun getDetailsNavigationDirection(item: Episode): NavDirections {
        TODO("Not yet implemented")
    }

}