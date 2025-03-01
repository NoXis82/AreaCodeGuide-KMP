package ru.noxis.areacodeguide.presentation.action

sealed interface RegionAction {
    data class OnSearchQueryChange(val query: String) : RegionAction
}