package com.example.pagingsample.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecorator(
    offset: Int,
) : RecyclerView.ItemDecoration() {

    private val space = offset / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = space
            bottom = space
            left = space
            right = space
        }
    }
}