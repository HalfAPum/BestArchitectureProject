package com.example.pagingsample.ui.fragment

import androidx.navigation.NavDirections
import com.example.pagingsample.model.Location
import com.example.pagingsample.ui.adapter.LocationPagingAdapter
import com.example.pagingsample.ui.fragment.base.PagingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : PagingFragment<Location>() {

    override val adapter by lazy { LocationPagingAdapter() }

    override fun getDetailsNavigationDirection(item: Location): NavDirections {
        TODO("Not yet implemented")
    }

}