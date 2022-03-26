package com.example.pagingsample.ui.adapter.base

import androidx.paging.PagingDataAdapter
import com.example.pagingsample.model.local.interfaces.Identifiable
import com.example.pagingsample.ui.diffutil.IdentifiableDiffUtil
import com.example.pagingsample.ui.viewholder.base.BaseItemViewHolder

abstract class BasePagingAdapter<T : Identifiable> : PagingDataAdapter<T, BaseItemViewHolder<T>>(
    IdentifiableDiffUtil()
) {

    override fun onBindViewHolder(holder: BaseItemViewHolder<T>, position: Int) {
        getItem(position)?.let { holder.update(it) }
    }

}