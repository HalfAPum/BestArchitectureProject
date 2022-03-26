package com.example.pagingsample.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.ui.adapter.base.BasePagingAdapter
import com.example.pagingsample.ui.diffutil.IdentifiableDiffUtil
import com.example.pagingsample.ui.viewholder.PassengerItemViewHolder

class PassengersPagingAdapter : BasePagingAdapter<Passenger>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerItemViewHolder {
        return PassengerItemViewHolder.create(parent)
    }

}