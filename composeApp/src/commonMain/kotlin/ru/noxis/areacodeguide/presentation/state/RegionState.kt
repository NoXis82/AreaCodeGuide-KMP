package ru.noxis.areacodeguide.presentation.state

import androidx.compose.runtime.Immutable
import ru.noxis.areacodeguide.core.presentation.UiText

@Immutable
data class RegionState(
    val searchQuery: String = "",
    val searchResults: String = "",
    val errorMessage: UiText? = null
)
