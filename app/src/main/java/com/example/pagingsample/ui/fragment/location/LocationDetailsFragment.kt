package com.example.pagingsample.ui.fragment.location

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pagingsample.R
import com.example.pagingsample.databinding.FragmentLocationDetailsBinding
import com.example.pagingsample.model.location.LocationWithDetails
import com.example.pagingsample.viewmodel.DetailsState
import com.example.pagingsample.viewmodel.DetailsUiAction
import com.example.pagingsample.viewmodel.LocationDetailsViewModel
//import dagger/.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

//@AndroidEntryPoint
class LocationDetailsFragment : Fragment(R.layout.fragment_location_details) {

    private val binding: FragmentLocationDetailsBinding by viewBinding()

    private val viewModel: LocationDetailsViewModel by viewModels()

    private val args: LocationDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(viewLifecycleOwner, state = ::render)
        viewModel.postAction(DetailsUiAction.Update(args.locationId))
    }

    private fun render(state: DetailsState) {
        if (state is DetailsState.Data<*>) {
            binding.bindUi(state.item as LocationWithDetails)
        }
    }

    private fun FragmentLocationDetailsBinding.bindUi(item: LocationWithDetails) {
        name.text = item.location.name
        type.text = getString(R.string.type, item.details.type)
        dimension.text = getString(R.string.dimension, item.details.dimension)
    }
}