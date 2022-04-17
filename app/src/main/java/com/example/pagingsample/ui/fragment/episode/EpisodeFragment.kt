package com.example.pagingsample.ui.fragment.episode

import androidx.fragment.app.viewModels
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.ui.adapter.paging.EpisodePagingAdapter
import com.example.pagingsample.ui.fragment.base.PagingFragment
import com.example.pagingsample.viewmodel.EpisodePagingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : PagingFragment<Episode>() {

    override val adapter by lazy { EpisodePagingAdapter() }

    override val viewModel: EpisodePagingViewModel by viewModels()

    override fun getDetailsNavigationDirection(id: Long) =
        EpisodeFragmentDirections.actionEpisodesDestToEpisodeDetailsFragment(id)

}