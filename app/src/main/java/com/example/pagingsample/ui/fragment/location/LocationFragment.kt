package com.example.pagingsample.ui.fragment.location

import com.example.pagingsample.model.location.Location
import com.example.pagingsample.ui.adapter.paging.LocationPagingAdapter
import com.example.pagingsample.ui.fragment.base.PagingFragment

//import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class LocationFragment : PagingFragment<Location>() {

    override val adapter by lazy { LocationPagingAdapter() }

//    override val viewModel: LocationPagingViewModel by viewModels()

    override fun getDetailsNavigationDirection(id: Long) =
        LocationFragmentDirections.actionLocationsDestToLocationDetailsFragment(id)

}