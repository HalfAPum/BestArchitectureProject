package com.example.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagingsample.repository.AirlineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AirlineViewModel @Inject constructor(
    private val airlineRepository: AirlineRepository,
): ViewModel() {

    fun subscribePassengers() = airlineRepository.getPassengersPagingData()
}