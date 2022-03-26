package com.example.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagingsample.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository,
): ViewModel() {

    fun subscribeCharacters() = rickAndMortyRepository.getCharactersPagingData()

}