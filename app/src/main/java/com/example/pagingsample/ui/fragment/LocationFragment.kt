package com.example.pagingsample.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentLocationBinding
import com.example.pagingsample.model.Location
import com.example.pagingsample.ui.adapter.LocationPagingAdapter
import com.example.pagingsample.ui.launchSubscribeFlow
import com.example.pagingsample.viewmodel.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LocationFragment : Fragment(R.layout.fragment_character) {

    private val viewModel : LocationViewModel by viewModels()

    private val binding: FragmentLocationBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = LocationPagingAdapter()
        adapter.onItemClick = ::onItemClick
        binding.recyclerView.adapter = adapter

        launchSubscribeFlow {
            viewModel.locationsFlow().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun onItemClick(item: Location) {
//        navigate(CharacterFragmentDirections.actionToDetails(item))
    }

}