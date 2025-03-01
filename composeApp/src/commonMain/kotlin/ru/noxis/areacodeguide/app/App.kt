package ru.noxis.areacodeguide.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import ru.noxis.areacodeguide.presentation.content.SplashContent
import ru.noxis.areacodeguide.presentation.viewmodel.SplashViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
//        val viewmodel = koinViewModel<RegionViewModel>()
//        SearchRegionContent(viewmodel)
        val viewModel = koinViewModel<SplashViewModel>()
        SplashContent(viewModel)
    }
}