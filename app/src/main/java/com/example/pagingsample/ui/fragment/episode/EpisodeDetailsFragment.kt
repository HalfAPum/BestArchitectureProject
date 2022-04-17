package com.example.pagingsample.ui.fragment.episode

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentEpisodeDetailsBinding
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.episode.EpisodeWithDetails
import com.example.pagingsample.ui.adapter.CharacterImageAdapter
import com.example.pagingsample.ui.decorator.GridItemDecorator
import com.example.pagingsample.ui.getDimen
import com.example.pagingsample.ui.navigate
import com.example.pagingsample.viewmodel.DetailsState
import com.example.pagingsample.viewmodel.DetailsUiAction
import com.example.pagingsample.viewmodel.EpisodeDetailsViewModel
import com.example.pagingsample.viewmodel.NavigationSideEffect
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class EpisodeDetailsFragment : Fragment(R.layout.fragment_episode_details) {

    private val binding: FragmentEpisodeDetailsBinding by viewBinding()

    private val viewModel: EpisodeDetailsViewModel by viewModels()

    private val args: EpisodeDetailsFragmentArgs by navArgs()

    private val adapter by lazy { CharacterImageAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::sideEffect)
        viewModel.postAction(DetailsUiAction.Update(args.episodeId))
    }

    private fun setUpUi() {
        with(binding) {
            recyclerView.addItemDecoration(GridItemDecorator(getDimen(R.dimen.grid_margin)))
            recyclerView.layoutManager = GridLayoutManager(requireContext(), GRID_SIZE)
            recyclerView.adapter = adapter
            recyclerView.clipToOutline = true
            adapter.onItemClick = ::onItemClick
        }
    }

    private fun onItemClick(item: Character) {
        viewModel.postAction(DetailsUiAction.Navigate(item.id))
    }

    private fun render(state: DetailsState) {
        if (state is DetailsState.Data<*>) {
            binding.bindUi(state.item as EpisodeWithDetails)
        }
    }

    private fun FragmentEpisodeDetailsBinding.bindUi(item: EpisodeWithDetails) {
        name.text = item.episode.name
        name.isSelected = true
        airDate.text = item.episode.airDate
        created.text = item.detailsWithCharacters.details.created
        episodeCode.text = item.detailsWithCharacters.details.episodeCode
        adapter.update(item.detailsWithCharacters.characters)
    }

    private fun sideEffect(sideEffect: NavigationSideEffect) {
        navigate(EpisodeDetailsFragmentDirections
            .actionEpisodeDetailsFragmentToCharacterDetailsFragment(
                sideEffect.id
            )
        )
    }

    companion object {
        private const val GRID_SIZE = 4
    }
}