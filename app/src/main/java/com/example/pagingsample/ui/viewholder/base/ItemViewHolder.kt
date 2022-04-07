package com.example.pagingsample.ui.viewholder.base

import com.example.pagingsample.utils.VoidCallback

interface ItemViewHolder<T> {

    fun update(item: T)

    fun setOnClickListener(action: VoidCallback)

}