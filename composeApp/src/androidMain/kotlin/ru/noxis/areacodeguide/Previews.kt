package ru.noxis.areacodeguide

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.noxis.areacodeguide.core.domain.DataError
import ru.noxis.areacodeguide.core.presentation.toUiText
import ru.noxis.areacodeguide.presentation.content.SearchRegionBox
import ru.noxis.areacodeguide.presentation.content.SplashBox
import ru.noxis.areacodeguide.presentation.state.RegionState
import ru.noxis.areacodeguide.presentation.state.SplashState

@Composable
@Preview
private fun SearchRegionBoxPreview() {
    SearchRegionBox(
        state = { RegionState(errorMessage = DataError.Local.UNKNOWN.toUiText()) },
        onAction = {},
        onImeSearch = {}
    )
}

@Composable
@Preview
private fun SplashBoxPreview() {
    SplashBox {
        SplashState()
    }
}