package com.example.pagingsample.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingsample.R
import com.example.pagingsample.ui.adapter.PassengersPagingAdapter
import com.example.pagingsample.ui.launchSubscribeFlow
import com.example.pagingsample.ui.subscribeFlow
import com.example.pagingsample.viewmodel.AirlineViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_passenger.*
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PassengerFragment : Fragment() {

    private val viewModel : AirlineViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PassengersPagingAdapter()
        recycler_view.adapter = adapter

        launchSubscribeFlow {
            viewModel.subscribePassengers().collectLatest {
                adapter.submitData(it)
            }
        }
    }

}