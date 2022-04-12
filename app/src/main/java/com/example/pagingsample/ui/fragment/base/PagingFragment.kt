package com.example.pagingsample.ui.fragment.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentItemBinding
import com.example.pagingsample.model.action.CardClickUiAction
import com.example.pagingsample.model.effect.NavigationSideEffect
import com.example.pagingsample.model.interfaces.Identifiable
import com.example.pagingsample.model.state.PagingState
import com.example.pagingsample.ui.adapter.base.BasePagingAdapter
import com.example.pagingsample.ui.launchSubscribeFlow
import com.example.pagingsample.ui.navigate
import com.example.pagingsample.viewmodel.PagingViewModel
import org.orbitmvi.orbit.viewmodel.observe

abstract class PagingFragment<T : Identifiable> : Fragment(R.layout.fragment_item) {

    protected abstract val adapter: BasePagingAdapter<T>

    private val viewModel: PagingViewModel<T> by viewModels()

    protected val binding: FragmentItemBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::sideEffect)
    }

    @CallSuper
    protected open fun bindViews() {
        adapter.onItemClick = ::onItemClick
        binding.recyclerView.adapter = adapter
    }

    private fun onItemClick(item: T) {
        viewModel.postAction(CardClickUiAction(item))
    }

    private fun render(state: PagingState<T>) {
        launchSubscribeFlow(state.data) {
            adapter.submitData(it)
        }
    }

    private fun sideEffect(sideEffect: NavigationSideEffect<T>) {
        navigate(getDetailsNavigationDirection(sideEffect.item))
    }

    protected abstract fun getDetailsNavigationDirection(item: T) : NavDirections
}