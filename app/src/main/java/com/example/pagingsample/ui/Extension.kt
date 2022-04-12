package com.example.pagingsample.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
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