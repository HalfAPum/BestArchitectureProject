package com.example.pagingsample.ui

import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Implied to use inside [LifecycleOwner].
 */
fun <T> LifecycleOwner.launchSubscribeFlow(flow: Flow<T>, block: suspend (item: T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest {
                block.invoke(it)
            }
        }
    }
}

fun Fragment.navigate(directions: NavDirections) {
    findNavController().navigate(directions)
}

fun Fragment.getDimen(@DimenRes dimenRes: Int): Int {
    return requireContext().resources.getDimensionPixelSize(dimenRes)
}

fun Fragment.getDimenFloat(@DimenRes dimenRes: Int): Float {
    return requireContext().resources.getDimension(dimenRes)
}

fun RecyclerView.ViewHolder.getString(
    @StringRes resId: Int,
    string: String?
): String {
    return itemView.getString(resId, string)
}

fun View.getString(
    @StringRes resId: Int,
    string: String?
): String {
    return context.getString(resId, string)
}