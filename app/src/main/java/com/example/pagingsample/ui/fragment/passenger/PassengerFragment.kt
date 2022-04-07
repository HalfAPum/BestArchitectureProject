package com.example.pagingsample.ui.fragment.passenger

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentPassengerBinding
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.ui.adapter.PassengersPagingAdapter
import com.example.pagingsample.ui.launchSubscribeFlow
import com.example.pagingsample.viewmodel.AirlineViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PassengerFragment : Fragment(R.layout.fragment_passenger) {

    private val viewModel : AirlineViewModel by viewModels()

    private val binding : FragmentPassengerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PassengersPagingAdapter()
        adapter.onItemClick = ::onItemClick
        binding.recyclerView.adapter = adapter

        launchSubscribeFlow {
            viewModel.subscribePassengers().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun onItemClick(item: Passenger) {

    }

}