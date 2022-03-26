package com.example.pagingsample.ui.viewholder.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseItemViewHolder<T>(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun update(item: T)

}