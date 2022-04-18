package com.example.pagingsample.ui.fragment.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentItemBinding
import com.example.pagingsample.model.interfaces.Identifiable
import com.example.pagingsample.ui.adapter.paging.base.BasePagingAdapter
import com.example.pagingsample.ui.launchSubscribeFlow
import com.example.pagingsample.ui.navigate
import com.example.pagingsample.viewmodel.NavigationSideEffect
import com.example.pagingsample.viewmodel.PagingState

abstract class PagingFragment<T : Identifiable> : Fragment(R.layout.fragment_item) {

    protected abstract val adapter: BasePagingAdapter<T>

//    protected abstract val viewModel: PagingViewModel<T>

    private val binding: FragmentItemBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
//        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = ::sideEffect)
    }

    @CallSuper
    protected open fun bindViews() {
        adapter.onItemClick = ::onItemClick
        val dividerDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(dividerDecorator)
        binding.recyclerView.adapter = adapter

    }

    private fun onItemClick(item: T) {
//        viewModel.postAction(CardClickUiAction(item))
    }

    private fun render(state: PagingState<T>) {
        launchSubscribeFlow(state.data) {
            adapter.submitData(it)
        }
    }

    private fun sideEffect(sideEffect: NavigationSideEffect) {
        navigate(getDetailsNavigationDirection(sideEffect.id))
    }

    protected abstract fun getDetailsNavigationDirection(id: Long) : NavDirections
}