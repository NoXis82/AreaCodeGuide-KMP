package ru.noxis.areacodeguide.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ru.noxis.areacodeguide.presentation.content.SearchRegionContent
import ru.noxis.areacodeguide.presentation.viewmodel.RegionViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewmodel = koinViewModel<RegionViewModel>()
        SearchRegionContent(viewmodel)
    }
}