package com.example.pagingsample.ui.fragment.location

import androidx.fragment.app.viewModels
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.ui.adapter.paging.LocationPagingAdapter
import com.example.pagingsample.ui.fragment.base.PagingFragment
import com.example.pagingsample.viewmodel.LocationPagingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : PagingFragment<Location>() {

    override val adapter by lazy { LocationPagingAdapter() }

    override val viewModel: LocationPagingViewModel by viewModels()

    override fun getDetailsNavigationDirection(id: String) =
        LocationFragmentDirections.actionLocationsDestToLocationDetailsFragment(id)

}