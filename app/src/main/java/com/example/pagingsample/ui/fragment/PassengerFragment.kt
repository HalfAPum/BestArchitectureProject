package com.example.pagingsample.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentPassengerBinding
import com.example.pagingsample.ui.adapter.PassengersPagingAdapter
import com.example.pagingsample.ui.launchSubscribeFlow
import com.example.pagingsample.ui.subscribeFlow
import com.example.pagingsample.viewmodel.AirlineViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PassengerFragment : Fragment() {

    private val viewModel : AirlineViewModel by viewModels()

    private val binding : FragmentPassengerBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PassengersPagingAdapter()
        binding.recyclerView.adapter = adapter

        launchSubscribeFlow {
            viewModel.subscribePassengers().collectLatest {
                adapter.submitData(it)
            }
        }
    }

}