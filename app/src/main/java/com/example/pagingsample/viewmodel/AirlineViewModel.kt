package com.example.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagingsample.repository.AirlineRepository
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@HiltViewModel
class AirlineViewModel @Inject constructor(
    private val airlineRepository: AirlineRepository,
): ViewModel() {

    fun subscribePassengers() = airlineRepository.getPassengersPagingData()

}