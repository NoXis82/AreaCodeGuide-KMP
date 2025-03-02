package ru.noxis.areacodeguide.presentation.state

import androidx.compose.runtime.Immutable
import ru.noxis.areacodeguide.core.presentation.UiText

@Immutable
data class SplashState(
    val isLoading: Boolean = false,
    val error: UiText? = null
)
