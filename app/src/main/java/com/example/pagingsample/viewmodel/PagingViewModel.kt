package com.example.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagingsample.model.Character
import com.example.pagingsample.model.Episode
import com.example.pagingsample.model.Location
import com.example.pagingsample.model.action.CardClickUiAction
import com.example.pagingsample.model.effect.NavigationSideEffect
import com.example.pagingsample.model.interfaces.Identifiable
import com.example.pagingsample.model.state.PagingState
import com.example.pagingsample.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

abstract class PagingViewModel<T : Identifiable> (
    private val itemRepository: ItemRepository<T>
) : ViewModel(), ContainerHost<PagingState<T>, NavigationSideEffect<T>> {

    override val container: Container<PagingState<T>, NavigationSideEffect<T>> =
        container(PagingState(getItemPagingFlow()))

    private fun getItemPagingFlow() = itemRepository.getItemPagingFlow()

    fun postAction(action: CardClickUiAction<T>) = intent {
        postSideEffect(NavigationSideEffect(action.item))
    }

}

@HiltViewModel
class CharacterPagingViewModel @Inject constructor(
    itemRepository: ItemRepository<Character>
) : PagingViewModel<Character>(itemRepository)

@HiltViewModel
class LocationPagingViewModel @Inject constructor(
    itemRepository: ItemRepository<Location>
) : PagingViewModel<Location>(itemRepository)

@HiltViewModel
class EpisodePagingViewModel @Inject constructor(
    itemRepository: ItemRepository<Episode>
) : PagingViewModel<Episode>(itemRepository)


















