package com.example.pagingsample.ui.adapter

import android.view.ViewGroup
import com.example.pagingsample.model.Location
import com.example.pagingsample.ui.adapter.base.BasePagingAdapter
import com.example.pagingsample.ui.viewholder.LocationItemViewHolder

class LocationPagingAdapter : BasePagingAdapter<Location>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationItemViewHolder {
        return LocationItemViewHolder.create(parent)
    }

}