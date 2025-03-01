package ru.noxis.areacodeguide

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.noxis.areacodeguide.presentation.content.SearchRegionBox
import ru.noxis.areacodeguide.presentation.state.RegionState

@Composable
@Preview
private fun SearchRegionBoxPreview() {
    SearchRegionBox(
        state = { RegionState() },
        onAction = {},
        onImeSearch = {}
    )
}