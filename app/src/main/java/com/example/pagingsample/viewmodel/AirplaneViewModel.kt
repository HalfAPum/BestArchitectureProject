package com.example.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagingsample.repository.AirplaneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AirplaneViewModel @Inject constructor(
    private val airplaneRepository: AirplaneRepository,
): ViewModel() {

    fun subscribePassengers() = airplaneRepository.getPassengersPagingData()
}