package com.example.pagingsample.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.pagingsample.utils.SuspendVoidCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Implied to use inside [LifecycleOwner].
 */
fun LifecycleOwner.launchSubscribeFlow(block: SuspendVoidCallback) {
    lifecycleScope.launch {
        subscribeFlow(block)
    }
}

/**
 * Implied to use inside [CoroutineScope] and [LifecycleOwner].
 */
suspend fun LifecycleOwner.subscribeFlow(block: SuspendVoidCallback) {
    repeatOnLifecycle(Lifecycle.State.STARTED) {
        block.invoke()
    }
}

fun Fragment.navigate(directions: NavDirections) {
    findNavController().navigate(directions)
}