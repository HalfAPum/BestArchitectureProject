package com.example.pagingsample.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pagingsample.model.api.Passenger
import com.example.pagingsample.ui.diffutil.PassengerDiffUtil
import com.example.pagingsample.ui.viewholder.PassengerItemViewHolder

class PassengersPagingAdapter : PagingDataAdapter<Passenger, PassengerItemViewHolder>(
    PassengerDiffUtil()
) {

    override fun onBindViewHolder(holder: PassengerItemViewHolder, position: Int) {
        getItem(position)?.let { holder.update(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerItemViewHolder {
        return PassengerItemViewHolder.create(parent)
    }

}