package com.example.pagingsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.model.episode.Episode
import com.example.pagingsample.model.interfaces.Identifiable
import com.example.pagingsample.model.location.Location
import com.example.pagingsample.repository.PagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

abstract class PagingViewModel<T : Identifiable> (
    private val pagingRepository: PagingRepository<T>
) : ViewModel(), ContainerHost<PagingState<T>, NavigationSideEffect> {

    override val container: Container<PagingState<T>, NavigationSideEffect> =
        container(PagingState(getItemPagingFlow()))

    private fun getItemPagingFlow() = pagingRepository.getItemPagingFlow()

    fun postAction(action: CardClickUiAction<T>) = intent {
        postSideEffect(NavigationSideEffect(action.item.id))
    }

}

data class CardClickUiAction<T>(val item: T)

data class PagingState<T : Any>(
    val data: Flow<PagingData<T>>
)

data class NavigationSideEffect(val id: String)


@HiltViewModel
class CharacterPagingViewModel @Inject constructor(
    itemRepository: PagingRepository<Character>
) : PagingViewModel<Character>(itemRepository)

@HiltViewModel
class LocationPagingViewModel @Inject constructor(
    itemRepository: PagingRepository<Location>
) : PagingViewModel<Location>(itemRepository)

@HiltViewModel
class EpisodePagingViewModel @Inject constructor(
    itemRepository: PagingRepository<Episode>
) : PagingViewModel<Episode>(itemRepository)