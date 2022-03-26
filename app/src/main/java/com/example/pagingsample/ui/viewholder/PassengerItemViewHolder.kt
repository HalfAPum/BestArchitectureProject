package com.example.pagingsample.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsample.R
import com.example.pagingsample.model.api.PassengerNetwork
import com.example.pagingsample.model.local.Passenger
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.passenger_item_layout.view.*

class PassengerItemViewHolder(view: View) :
    BaseItemViewHolder<Passenger>(view) {

    override fun update(item: Passenger) {
        with(itemView) {
            passenger_name.text = item.name
        }
    }

    companion object {

        fun create(parent: ViewGroup) = PassengerItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.passenger_item_layout,
                parent,
                false,
            )
        )
    }
}