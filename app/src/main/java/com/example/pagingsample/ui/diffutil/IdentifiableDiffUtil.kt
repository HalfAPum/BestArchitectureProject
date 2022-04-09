package com.example.pagingsample.ui.diffutil

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.pagingsample.model.interfaces.Identifiable

class IdentifiableDiffUtil<T>: DiffUtil.ItemCallback<T>() where T : Identifiable {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * Compare items using equals. If object doesn't override it, then exception will be thrown.
     */
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

}