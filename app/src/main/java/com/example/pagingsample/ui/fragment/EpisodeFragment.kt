package com.example.pagingsample.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentEpisodeBinding
import com.example.pagingsample.model.Episode
import com.example.pagingsample.ui.adapter.EpisodePagingAdapter
import com.example.pagingsample.ui.launchSubscribeFlow
import com.example.pagingsample.viewmodel.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class EpisodeFragment : Fragment(R.layout.fragment_episode) {

    private val viewModel : EpisodeViewModel by viewModels()

    private val binding: FragmentEpisodeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = EpisodePagingAdapter()
        adapter.onItemClick = ::onItemClick
        binding.recyclerView.adapter = adapter

        launchSubscribeFlow {
            viewModel.episodesFlow().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun onItemClick(item: Episode) {
//        navigate(CharacterFragmentDirections.actionToDetails(item))
    }

}