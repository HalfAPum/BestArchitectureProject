package com.example.pagingsample.ui.adapter.base

import androidx.paging.PagingDataAdapter
import com.example.pagingsample.model.interfaces.Identifiable
import com.example.pagingsample.ui.diffutil.IdentifiableDiffUtil
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder
import com.example.pagingsample.utils.TypedVoidCallback

abstract class BasePagingAdapter<T : Identifiable> : PagingDataAdapter<T, BaseItemViewHolder<T>>(
    IdentifiableDiffUtil()
) {

    var onItemClick: TypedVoidCallback<T> = {}

    override fun onBindViewHolder(holder: BaseItemViewHolder<T>, position: Int) {
        with(holder) {
            getItem(position)?.let {
                update(it)
                setOnClickListener { onItemClick.invoke(it) }
            }
        }
    }

}