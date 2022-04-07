package com.example.pagingsample.ui.viewholder.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingsample.utils.VoidCallback

abstract class BaseItemViewHolder<T>(
    view: View
) : RecyclerView.ViewHolder(view), ItemViewHolder<T> {

    override fun setOnClickListener(action: VoidCallback) {
        itemView.setOnClickListener {
            action.invoke()
        }
    }

}