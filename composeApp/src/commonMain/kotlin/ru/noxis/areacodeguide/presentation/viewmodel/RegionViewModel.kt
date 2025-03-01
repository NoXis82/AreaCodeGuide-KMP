package ru.noxis.areacodeguide.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.noxis.areacodeguide.core.domain.onError
import ru.noxis.areacodeguide.core.domain.onSuccess
import ru.noxis.areacodeguide.core.presentation.toUiText
import ru.noxis.areacodeguide.domain.RegionsRepository
import ru.noxis.areacodeguide.presentation.action.RegionAction
import ru.noxis.areacodeguide.presentation.state.RegionState

class RegionViewModel(
    private val regionsRepository: RegionsRepository
) : ViewModel() {

    private var searchJob: Job? = null

    private val _state = MutableStateFlow(RegionState())
    val state = _state.onStart {
        observeSearchQuery()
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)


    fun onAction(action: RegionAction) {
        when (action) {
            is RegionAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
        }
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() || query.length == 1 -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResults = ""
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchRegion(query)
                    }
                }
            }.launchIn(viewModelScope)
    }


    private fun searchRegion(query: String) = viewModelScope.launch {
        regionsRepository.getRegionByCode(query)
            .onSuccess { searchResult ->
                _state.update {
                    it.copy(
                        searchResults = searchResult,
                        errorMessage = null
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        searchResults = "",
                        errorMessage = error.toUiText()
                    )
                }
            }
    }
}
